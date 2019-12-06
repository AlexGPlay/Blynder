package atrahasis.core.chromium.injector;

import java.io.File;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class Compiler {

	private JavaCompiler compiler;
	
	public Compiler() {
		this.compiler = ToolProvider.getSystemJavaCompiler();
	}
	
	public void compile(File toCompile) {
		executeCompilation(toCompile);
	}
	
	public void compile(List<File> toCompile) {
		toCompile.forEach(this::compile);
	}
	
	public void compile(String toCompile) {
		compile(new File(toCompile));
	}
	
	public void compileFolder(String folder) {
		File actualFolder = new File(folder);
		for(File f : actualFolder.listFiles()) {
			this.compile(f);
		}
	}
	
	private void executeCompilation(File toCompile) {
		compiler.run(null, null, null, toCompile.getPath());
	}
	
}
