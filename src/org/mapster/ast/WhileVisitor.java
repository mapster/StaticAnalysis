package org.mapster.ast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.tools.Diagnostic;

import com.sun.source.tree.AssignmentTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.CompoundAssignmentTree;
import com.sun.source.tree.Tree.Kind;
import com.sun.source.tree.UnaryTree;
import com.sun.source.tree.WhileLoopTree;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.Trees;

public class WhileVisitor extends TreePathScanner<Set<String>, Trees> {
	private CompilationUnitTree compilationUnit;

	public Set<String> scan(Iterable<? extends CompilationUnitTree> nodes, Trees trees){
        Set<String> r = null;
        if (nodes != null) {
            boolean first = true;
            for (CompilationUnitTree node : nodes) {
            	this.compilationUnit = node;
                r = (first ? scan(node, trees) : reduce(scan(node, trees), r));
                first = false;
            }
        }
        return r;
	}
	
	@Override
	public Set<String> reduce(Set<String> s1, Set<String> s2) {
		if(s1 == null)
			s1 = new HashSet<>();
		if(s2 == null)
			s2= new HashSet<>();
		s1.addAll(s2);
		return s1;
	}

	@Override
	public Set<String> visitWhileLoop(WhileLoopTree whileLoopTree, Trees trees) {
		Set<String> assignments = super.visitWhileLoop(whileLoopTree, trees);
		
		IdentifierScanner sc = new IdentifierScanner();
		Set<String> conditions = sc.scan(whileLoopTree.getCondition(), trees);

		if(conditions.isEmpty()){
			String msg = "The condition of the while-loop has a conststant expression. (i.e. no variables are used)";
			trees.printMessage(Diagnostic.Kind.WARNING, msg, whileLoopTree.getCondition(), compilationUnit);
			
		}
		else {
			conditions.retainAll(assignments);
			if(conditions.isEmpty()){
				String msg = ("None of the variables in the while-loop condition are subject to change (assignment) in the loop-body.");
				trees.printMessage(Diagnostic.Kind.WARNING, msg, whileLoopTree.getStatement(), compilationUnit);
			}
		}
		return assignments;
	}

	@Override
	public Set<String> visitAssignment(AssignmentTree assignmentTree, Trees trees) {
		Set<String> s = new HashSet<>();
		s.add(assignmentTree.getVariable().toString());
		return s;
	}

	@Override
	public Set<String> visitUnary(UnaryTree unaryTree, Trees trees) {
		Set<String> s = new HashSet<>();
		if(unaryTree.getKind().equals(Kind.POSTFIX_DECREMENT) || 
		   unaryTree.getKind().equals(Kind.POSTFIX_INCREMENT) ||
		   unaryTree.getKind().equals(Kind.PREFIX_DECREMENT)  ||
		   unaryTree.getKind().equals(Kind.PREFIX_INCREMENT)){
			IdentifierScanner sc = new IdentifierScanner();
			s.addAll(sc.scan(unaryTree.getExpression(), trees));
		}
		return s;
	}

	@Override
	public Set<String> visitCompoundAssignment(CompoundAssignmentTree assignmentTree, Trees trees) {
		return new HashSet<>(Arrays.asList(assignmentTree.getVariable().toString()));
	}

}
