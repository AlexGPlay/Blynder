package org.blynder.core.finder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * ClassFinder is the built-in implementation of the IClassFinder interface.
 * This class provides a find method that looks for all the classes in the project.
 *
 */
public class ClassFinder implements IClassFinder{

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
	
	/**
	 * 
	 * This method will check if all the files we checked in the other steps
	 * really exists, if there is at least one class that doesn't exist 
	 * this method will return 1 and a ClassNotFoundException will be raised.
	 * @param classes
	 * The list of the classes indexed before.
	 * @return
	 * 1 if there is class that doesn't exist.<br>
	 * 0 if all the classes exist.
	 * 
	 */
	private int checkClasses(List<Class<?>> classes) {
		for(Class<?> clazz : classes)
			if(clazz == null)
				return 1;
		
		return 0;
	}
	
	/**
	 * 
	 * Given the java files returned from the convertFiles method, it 
	 * transforms the files into classes. For example, given a file such
	 * as "code.main", it will transform it into the Main class.
	 * @param names
	 * A list that contains the java files with their package.
	 * @return
	 * A list of classes.
	 * 
	 */
	private List<Class<?>> convertToClasses(List<String> names){
		return names
		.stream()
		.map( this::classFromName )
		.collect( Collectors.toList() );
	}
	
	/**
	 * 
	 * Given a package class it will transform it into the class itself.
	 * @param name
	 * The package name.
	 * @return
	 * The class of the package.
	 * 
	 */
	private Class<?> classFromName(String name) {
		try {
			return Class.forName(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * Given the relative paths to the files of the project, this method
	 * will replace some characters so instead of having relative paths it
	 * will have the packages of the files.
	 * For example, given a relative path such as "/src/code/main.java", this
	 * method will return code.main so it will be later transformed into 
	 * a real class.
	 * @param files
	 * A list that contains the relative paths of the files.
	 * @return
	 * A list that contains the files with their packages.
	 * 
	 */
	private List<String> convertFiles(List<String> files){
		return files
		.stream()
		.map( f -> f.replace("." + File.separator + "src" + File.separator, "") )
		.map( f -> f.replace("main" + File.separator + "java" + File.separator, "") )
		.map( f -> f.replace(File.separator, ".") )
		.map( f -> f.replace(".java", "") )
		.collect( Collectors.toList() );
	}
	
	/**
	 * 
	 * Given a project this method can list all the source files that are in the
	 * src folder of the project.
	 * @return
	 * A list that contains all the relative paths to the files of the project.
	 * 
	 */
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
