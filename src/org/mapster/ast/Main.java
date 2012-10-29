package org.mapster.ast;

import java.io.*;
import java.util.ArrayList;

import javax.tools.*;
import javax.tools.JavaCompiler.CompilationTask;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.mapster.myast.*;

import com.google.gson.JsonElement;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.util.*;


public class Main {
	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException, WriteToStreamFailure {
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

		Trees trees = Trees.instance(task);
////  		visitor.scan(javacTask.parse(), trees);
//		for(CompilationUnitTree cuTree : javacTask.parse()){
//			System.out.println(cuTree.getPackageName());
//			for(Tree tr1: cuTree.getTypeDecls()){
//				ClassTree ct = (ClassTree)tr1;
//				ClassNode n = ClassNode.buildClass(ct);
//			}
//		}
		
		XmlDocument xmlDoc = new XmlDocument();
		JsonDocument jsonDoc = new JsonDocument();
		
		MyTreePathScanner<JsonElement> scanner = new MyTreePathScanner<>(jsonDoc);
		for(CompilationUnitTree tree: javacTask.parse()){
			scanner.buildDocument(tree, trees);
		}

		jsonDoc.writeToStream(System.out);
//		xmlDoc.writeToStream(System.out);
	}
}
