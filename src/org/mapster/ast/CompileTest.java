package org.mapster.ast;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.util.*;
import com.sun.tools.javac.api.JavacTaskImpl;

import demo.codeanalyzer.processor.CodeAnalyzerTreeVisitor;

public class CompileTest {

	public static void main(String[] args) throws IOException {
		// //Get an instance of java compiler
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				null, null, null);
		// Get a new instance of the standard file manager implementation

		// Get the list of java file objects, in this case we have only
		// one file, TestClass.java
		Iterable<? extends JavaFileObject> compilationUnits = fileManager
				.getJavaFileObjectsFromStrings(Arrays
						.asList("/home/mapster/eclipses/workspaces/ProcessorTest/srctest/org/mapster/myproj/Main.java"));

		CompilationTask task = compiler.getTask(null, fileManager, null, null,
				null, compilationUnits);
		JavacTask javacTask = (JavacTask) task;

		WhileVisitor visitor = new WhileVisitor();
		Trees trees = Trees.instance(task);
		int i = 0;
		visitor.scan(javacTask.parse(), trees);
	}
}
