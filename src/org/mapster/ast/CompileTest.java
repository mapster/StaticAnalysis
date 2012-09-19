package org.mapster.ast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.sun.source.util.JavacTask;
import com.sun.source.util.Trees;

public class CompileTest {

	public static void main(String[] args) throws IOException {
		ArrayList<File> sourceFiles = new ArrayList<>();
		if(args.length == 0){
			System.out.println("Please specify java source files to analyse.");
			return;
		}
		else {
			for(String s: args){
				if(!s.endsWith(".java")){
					System.out.println("All arguments must be java files.");
					return;
				}
				File f = new File(s);
				if(!f.isFile()){
					System.out.println("Could not find file with path: " +f.getAbsolutePath());
					return;
				}
				sourceFiles.add(f);
			}
		}
		
		// //Get an instance of java compiler
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				null, null, null);
		// Get a new instance of the standard file manager implementation

		// Get the list of java file objects, in this case we have only
		// one file, TestClass.java
		Iterable<? extends JavaFileObject> compilationUnits = fileManager
				.getJavaFileObjectsFromFiles(sourceFiles);

		CompilationTask task = compiler.getTask(null, fileManager, null, null,
				null, compilationUnits);
		JavacTask javacTask = (JavacTask) task;

		WhileVisitor visitor = new WhileVisitor();
		Trees trees = Trees.instance(task);
  		visitor.scan(javacTask.parse(), trees);
	}
}
