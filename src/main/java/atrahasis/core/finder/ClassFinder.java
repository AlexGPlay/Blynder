package atrahasis.core.finder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassFinder {

	public List<Class<?>> findClasses() throws ClassNotFoundException{
		List<String> files = findFiles();
		files = convertFiles(files);
		List<Class<?>> classes = convertToClasses(files);
		int res = checkClasses(classes);
		
		if(res == 1)
			throw new ClassNotFoundException("Se ha producido un error indexando las clases");
		
		else
			return classes;
	}
	
	private int checkClasses(List<Class<?>> classes) {
		for(Class<?> clazz : classes)
			if(clazz == null)
				return 1;
		
		return 0;
	}
	
	private List<Class<?>> convertToClasses(List<String> names){
		return names
		.stream()
		.map( this::classFromName )
		.collect( Collectors.toList() );
	}
	
	private Class<?> classFromName(String name) {
		try {
			return Class.forName(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private List<String> convertFiles(List<String> files){
		return files
		.stream()
		.map( f -> f.replace("." + File.separator + "src" + File.separator, "") )
		.map( f -> f.replace("main" + File.separator + "java" + File.separator, "") )
		.map( f -> f.replace(File.separator, ".") )
		.map( f -> f.replace(".java", "") )
		.collect( Collectors.toList() );
	}
	
	private List<String> findFiles() {
		try (Stream<Path> paths = Files.walk(Paths.get("./src"))) {
		    return paths
		    .filter( Files::isRegularFile )
		    .filter( f -> !f.toString().contains("resources") )
		    .map( p -> p.toString() )
		    .collect( Collectors.toList() );
		} 
		catch (IOException e) {
			return new ArrayList<>();
		} 
	}
	
}
