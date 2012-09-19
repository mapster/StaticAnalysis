package org.mapster.ast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.tools.Diagnostic;

import com.sun.source.tree.AssignmentTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.CompoundAssignmentTree;
import com.sun.source.tree.ConditionalExpressionTree;
import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.Tree.Kind;
import com.sun.source.tree.UnaryTree;
import com.sun.source.tree.VariableTree;
import com.sun.source.tree.WhileLoopTree;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.Trees;

public class WhileVisitor extends TreePathScanner<Set<String>, Trees> {
	private CompilationUnitTree compilationUnit;

//	public Set<String> scan(CompilationUnitTree compilationUnit, Trees trees){
//		this.compilationUnit = compilationUnit;
//		System.out.println("i egen scan");
//		return super.scan(compilationUnit, trees);
//	}
	
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
	public Set<String> reduce(Set<String> arg0, Set<String> arg1) {
		if(arg0 == null)
			arg0 = new HashSet<>();
		if(arg1 == null)
			arg1= new HashSet<>();
		arg0.addAll(arg1);
		return arg0;
	}

	@Override
	public Set<String> visitWhileLoop(WhileLoopTree arg0, Trees arg1) {
		Set<String> assignments = super.visitWhileLoop(arg0, arg1);
		
		IdentifierScanner sc = new IdentifierScanner();
		Set<String> conditions = sc.scan(arg0.getCondition(), arg1);

		if(conditions.isEmpty()){
			String msg = "The condition of the while-loop has a conststant expression. (i.e. no variables are used)";
			arg1.printMessage(Diagnostic.Kind.WARNING, msg, arg0.getCondition(), compilationUnit);
			
		}
		else {
			conditions.retainAll(assignments);
			if(conditions.isEmpty()){
				String msg = ("None of the variables in the while-loop condition are subject to change (assignment) in the loop-body.");
				arg1.printMessage(Diagnostic.Kind.WARNING, msg, arg0.getStatement(), compilationUnit);
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
	public Set<String> visitUnary(UnaryTree arg0, Trees arg1) {
		Set<String> s = new HashSet<>();
		if(arg0.getKind().equals(Kind.POSTFIX_DECREMENT) || 
		   arg0.getKind().equals(Kind.POSTFIX_INCREMENT) ||
		   arg0.getKind().equals(Kind.PREFIX_DECREMENT)  ||
		   arg0.getKind().equals(Kind.PREFIX_INCREMENT)){
			IdentifierScanner sc = new IdentifierScanner();
			s.addAll(sc.scan(arg0.getExpression(), arg1));
		}
		return s;
	}

	@Override
	public Set<String> visitCompoundAssignment(CompoundAssignmentTree arg0,	Trees arg1) {
		return new HashSet<>(Arrays.asList(arg0.getVariable().toString()));
	}
	
	

//	@Override
//	public Set<String> visitBinary(BinaryTree arg0, Trees arg1) {
//		Set<String> s = new HashSet<>();
//		System.out.println("visiting: " + arg0.toString() + ", " + arg0.getLeftOperand().toString() + ":" + arg0.getLeftOperand().getKind() + ", " + arg0.getRightOperand().toString() + ":" + arg0.getRightOperand().getKind());
//		s.add(arg0.toString());
//		return super.visitBinary(arg0, arg1);
//	}

//	@Override
//	public Set<String> visitIdentifier(IdentifierTree arg0, Trees arg1) {
//		Set<String> s = new HashSet<>();
//		s.add(arg0.getName().toString());
//		return s;
//	}
	

}
