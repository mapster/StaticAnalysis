package org.mapster.ast;
import java.util.*;

import javax.lang.model.element.Modifier;
import javax.xml.parsers.ParserConfigurationException;

import org.mapster.myast.*;

import com.sun.source.tree.*;
import com.sun.source.tree.Tree.Kind;
import com.sun.source.util.Trees;


public class MyTreePathScanner<E> implements TreeVisitor<AstIntermediaryNode<E>, Trees> {
	private CompilationUnitTree compilationUnit;
	public final AstIntermediary<E> document;

	public MyTreePathScanner(AstIntermediary<E> document) throws ParserConfigurationException{
		this.document = document;
	}

	public void addUnitToDocument(CompilationUnitTree treeNode, Trees trees) {
		this.compilationUnit = treeNode;
		AstIntermediaryNode<E> unit = document.createNode("compile_unit");
		
		if(treeNode.getPackageName() != null){
			unit.setProperty("package", treeNode.getPackageName().accept(this, trees));
		}
		for(Tree tree: treeNode.getImports()){
			unit.addToProperty("imports", tree.accept(this, trees));
		}
		for(Tree node: treeNode.getTypeDecls()){
			unit.addToProperty("type_declarations", node.accept(this, trees));
		}
		document.appendToTopLevel(unit);
	}

	public AstIntermediaryNode<E> scan(Tree node, Trees trees){
		return (node == null ? null : node.accept(this, trees));
	}
	
	public List<AstIntermediaryNode<E>> scan(List<? extends Tree> nodes, Trees trees){
		if(nodes == null)
			return null;
		List<AstIntermediaryNode<E>> elements = new LinkedList<>();
		for(Tree n: nodes){
			elements.add(scan(n, trees));
		}
		return elements;
	}
	
	@Override
	public AstIntermediaryNode<E> visitAnnotation(AnnotationTree arg0, Trees trees) {
		// TODO Auto-generated method stub
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitArrayAccess(ArrayAccessTree arrayAccessTree, Trees trees) {
		AstIntermediaryNode<E> arrayAccessNode = document.createNode("array_access");
		setPosition(arrayAccessNode, arrayAccessTree, trees);
		
		arrayAccessNode.setProperty("index", scan(arrayAccessTree.getIndex(), trees));
		arrayAccessNode.setProperty("expr", scan(arrayAccessTree.getExpression(), trees));
		
		return arrayAccessNode;
	}

	@Override
	public AstIntermediaryNode<E> visitArrayType(ArrayTypeTree arrayTypeTree, Trees trees) {
		AstIntermediaryNode<E> arrayTypeNode = document.createNode("array");
		setPosition(arrayTypeNode, arrayTypeTree, trees);
		
		arrayTypeNode.setProperty("value", scan(arrayTypeTree.getType(), trees));
		return arrayTypeNode;
	}

	@Override
	public AstIntermediaryNode<E> visitAssert(AssertTree assertTree, Trees trees) {
		AstIntermediaryNode<E> assertNode = document.createNode("assert");
		setPosition(assertNode, assertTree, trees);
		
		assertNode.setProperty("condition", scan(assertTree.getCondition(), trees));
		assertNode.setProperty("detail", scan(assertTree.getDetail(), trees));
		
		return assertNode;
	}

	@Override
	public AstIntermediaryNode<E> visitAssignment(AssignmentTree assignmentTree, Trees trees) {
		AstIntermediaryNode<E> assignmentNode = document.createNode("assignment");
		setPosition(assignmentNode, assignmentTree, trees);

		assignmentNode.setProperty("variable", scan(assignmentTree.getVariable(), trees));
		assignmentNode.setProperty("expr", scan(assignmentTree.getExpression(), trees));
		
		return assignmentNode;
	}

	@Override
	public AstIntermediaryNode<E> visitBinary(BinaryTree binaryTree, Trees trees) {
		AstIntermediaryNode<E> binaryNode = document.createNode("binary");
		setPosition(binaryNode, binaryTree, trees);
		
		binaryNode.setProperty("type", binaryTree.getKind().toString());
		binaryNode.setProperty("left_op", scan(binaryTree.getLeftOperand(), trees));
		binaryNode.setProperty("right_op", scan(binaryTree.getRightOperand(), trees));
		
		return binaryNode;
	}

	@Override
	public AstIntermediaryNode<E> visitBlock(BlockTree blockTree, Trees trees) {
		AstIntermediaryNode<E> blockNode = document.createNode("block");
		setPosition(blockNode, blockTree, trees);
		
		blockNode.setProperty("statements", scan(blockTree.getStatements(), trees));
		return blockNode;
	}

	@Override
	public AstIntermediaryNode<E> visitBreak(BreakTree breakTree, Trees trees) {
		AstIntermediaryNode<E> breakNode = document.createNode("break");
		setPosition(breakNode, breakTree, trees);

		return breakNode;		
	}

	@Override
	public AstIntermediaryNode<E> visitCase(CaseTree caseTree, Trees trees) {
		AstIntermediaryNode<E> caseNode = document.createNode("case");
		setPosition(caseNode, caseTree, trees);
		
		caseNode.setProperty("expr", scan(caseTree.getExpression(), trees));
		caseNode.setProperty("statements", scan(caseTree.getStatements(), trees));
		
		return caseNode;
	}

	@Override
	public AstIntermediaryNode<E> visitCatch(CatchTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitClass(ClassTree classTree, Trees trees) {
		AstIntermediaryNode<E> classNode = document.createNode("class");
		setPosition(classNode, classTree, trees);
		
		classNode.setProperty("modifiers", scan(classTree.getModifiers(), trees));
		classNode.setProperty("name", classTree.getSimpleName().toString());
		classNode.setProperty("members", scan(classTree.getMembers(), trees));
		
		return classNode;
	}

	@Override
	public AstIntermediaryNode<E> visitCompilationUnit(CompilationUnitTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitCompoundAssignment(CompoundAssignmentTree compAssignmentTree, Trees trees) {
		AstIntermediaryNode<E> compAssignmentNode = document.createNode("compound_assignment");
		setPosition(compAssignmentNode, compAssignmentTree, trees);
		
		compAssignmentNode.setProperty("type", compAssignmentTree.getKind().toString());
		compAssignmentNode.setProperty("expr", scan(compAssignmentTree.getExpression(), trees));
		compAssignmentNode.setProperty("variable", scan(compAssignmentTree.getVariable(), trees));
		
		return compAssignmentNode;
	}

	@Override
	public AstIntermediaryNode<E> visitConditionalExpression(ConditionalExpressionTree condExprTree, Trees trees) {
		AstIntermediaryNode<E> condExprNode = document.createNode("conditional_expr");
		setPosition(condExprNode, condExprTree, trees);
		
		condExprNode.setProperty("condition", scan(condExprTree.getCondition(), trees));
		condExprNode.setProperty("true_expr", scan(condExprTree.getTrueExpression(), trees));
		condExprNode.setProperty("false_expr", scan(condExprTree.getFalseExpression(), trees));
		
		return condExprNode;
	}

	@Override
	public AstIntermediaryNode<E> visitContinue(ContinueTree continueTree, Trees trees) {
		AstIntermediaryNode<E> continueNode = document.createNode("continue");
		setPosition(continueNode, continueTree, trees);

		return continueNode;
	}

	@Override
	public AstIntermediaryNode<E> visitDoWhileLoop(DoWhileLoopTree doWhileTree, Trees trees) {
		AstIntermediaryNode<E> doWhileNode = document.createNode("do-while");
		setPosition(doWhileNode, doWhileTree, trees);

		doWhileNode.setProperty("condition", scan(doWhileTree.getCondition(), trees));
		doWhileNode.setProperty("statement", scan(doWhileTree.getStatement(), trees));
		
		return doWhileNode;
	}

	@Override
	public AstIntermediaryNode<E> visitEmptyStatement(EmptyStatementTree emptyStatTree, Trees trees) {
		AstIntermediaryNode<E> emptyStatNode = document.createNode("empty");
		setPosition(emptyStatNode, emptyStatTree, trees);

		return emptyStatNode;
	}

	@Override
	public AstIntermediaryNode<E> visitEnhancedForLoop(EnhancedForLoopTree forLoopTree, Trees trees) {
		AstIntermediaryNode<E> forLoopNode = document.createNode("enhanced_for_loop");
		setPosition(forLoopNode, forLoopTree, trees);

		forLoopNode.setProperty("variable", scan(forLoopTree.getVariable(), trees));
		forLoopNode.setProperty("expr", scan(forLoopTree.getExpression(), trees));
		forLoopNode.setProperty("statement", scan(forLoopTree.getStatement(), trees));

		return forLoopNode;
	}

	@Override
	public AstIntermediaryNode<E> visitErroneous(ErroneousTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitExpressionStatement(ExpressionStatementTree statTree, Trees trees) {
		return scan(statTree.getExpression(), trees);
	}

	@Override
	public AstIntermediaryNode<E> visitForLoop(ForLoopTree forLoopTree, Trees trees) {
		AstIntermediaryNode<E> forLoopNode = document.createNode("for_loop");
		setPosition(forLoopNode, forLoopTree, trees);

		forLoopNode.setProperty("initializer", scan(forLoopTree.getInitializer(), trees));
		forLoopNode.setProperty("condition", scan(forLoopTree.getCondition(), trees));
		forLoopNode.setProperty("update", scan(forLoopTree.getUpdate(), trees));
		forLoopNode.setProperty("statement", scan(forLoopTree.getStatement(), trees));
		
		return forLoopNode;
	}

	@Override
	public AstIntermediaryNode<E> visitIdentifier(IdentifierTree idTree, Trees trees) {
		AstIntermediaryNode<E> idNode = document.createNode("identifier");
		setPosition(idNode, idTree, trees);
		
		idNode.setProperty("value", idTree.getName().toString());
		return idNode;
	}

	@Override
	public AstIntermediaryNode<E> visitIf(IfTree ifTree, Trees trees) {
		AstIntermediaryNode<E> ifNode = document.createNode("if");
		setPosition(ifNode, ifTree, trees);
		
		ifNode.setProperty("condition", scan(ifTree.getCondition(), trees));
		ifNode.setProperty("else", scan(ifTree.getElseStatement(), trees));
		ifNode.setProperty("then", scan(ifTree.getThenStatement(), trees));

		return ifNode;
	}

	@Override
	public AstIntermediaryNode<E> visitImport(ImportTree importTree, Trees trees) {
		AstIntermediaryNode<E> importNode = document.createNode("import");
		
		importNode.setProperty("id", importTree.getQualifiedIdentifier().accept(this, trees));
		importNode.setProperty("static", Boolean.toString(importTree.isStatic()));
		
		return importNode;
	}

	@Override
	public AstIntermediaryNode<E> visitInstanceOf(InstanceOfTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitLabeledStatement(LabeledStatementTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitLiteral(LiteralTree litTree, Trees trees) {
		AstIntermediaryNode<E> litNode = document.createNode("literal");
		setPosition(litNode, litTree, trees);
		
		litNode.setProperty("type", litTree.getKind().toString());
		if(litTree.getValue() != null){
			String value = litTree.getValue().toString();
			if(litTree.getKind().equals(Kind.CHAR_LITERAL))
				value = Integer.toString((int)((Character)litTree.getValue()));
				
			litNode.setProperty("value", value);
		}
		return litNode;
	}

	@Override
	public AstIntermediaryNode<E> visitMemberSelect(MemberSelectTree memberSelTree, Trees trees) {
		AstIntermediaryNode<E> memberSelNode = document.createNode("member_select");
		setPosition(memberSelNode, memberSelTree, trees);
		
		memberSelNode.setProperty("expr", scan(memberSelTree.getExpression(), trees));
		memberSelNode.setProperty("member_id", memberSelTree.getIdentifier().toString());
		return memberSelNode;
	}

	@Override
	public AstIntermediaryNode<E> visitMethod(MethodTree methodTree, Trees trees) {
		AstIntermediaryNode<E> methodNode = document.createNode("method");
		setPosition(methodNode, methodTree, trees);
		
		//throw exception if defaultValue is set (we don't handle it)
		if(methodTree.getDefaultValue() != null){
			throw new NotDefinedYetException("Method default value action not implemented: " + methodTree.getDefaultValue());
		}
		if(methodTree.getTypeParameters() != null && !methodTree.getTypeParameters().isEmpty()){
			throw new NotDefinedYetException("Method type parameters not implemented: " + methodTree.getTypeParameters());
		}
		
		methodNode.setProperty("modifiers", scan(methodTree.getModifiers(), trees));
		methodNode.setProperty("name", methodTree.getName().toString());
		methodNode.setProperty("type", scan(methodTree.getReturnType(), trees));
		methodNode.setProperty("parameters", scan(methodTree.getParameters(), trees));
		methodNode.setProperty("body", scan(methodTree.getBody(), trees));
		
		return methodNode;
	}
	
	@Override
	public AstIntermediaryNode<E> visitMethodInvocation(MethodInvocationTree methodCallTree, Trees trees) {
		AstIntermediaryNode<E> methodCallNode = document.createNode("method_call");
		setPosition(methodCallNode, methodCallTree, trees);
		
		methodCallNode.setProperty("select", scan(methodCallTree.getMethodSelect(), trees));
		methodCallNode.setProperty("arguments", scan(methodCallTree.getArguments(), trees));

		if(methodCallTree.getTypeArguments() != null && !methodCallTree.getTypeArguments().isEmpty())
			throw new NotDefinedYetException("Type arguments for method call not implemented: " + methodCallTree.getTypeArguments());

		return methodCallNode;
	}

	@Override
	public AstIntermediaryNode<E> visitModifiers(ModifiersTree modifiersTree, Trees trees) {
		if(modifiersTree.getFlags() == null || modifiersTree.getFlags().isEmpty())
			return null;
		
		AstIntermediaryNode<E> modifiersNode = document.createNode("modifiers");
		setPosition(modifiersNode, modifiersTree, trees);
		
		for(Modifier m: modifiersTree.getFlags())
			modifiersNode.addToProperty("modifiers", m.toString());
		
		return modifiersNode;		
	}

	@Override
	public AstIntermediaryNode<E> visitNewArray(NewArrayTree newArrayTree, Trees trees) {
		AstIntermediaryNode<E> newArrayNode = document.createNode("new_array");
		setPosition(newArrayNode, newArrayTree, trees);
		
		newArrayNode.setProperty("type", scan(newArrayTree.getType(), trees));
		newArrayNode.setProperty("dimensions",scan(newArrayTree.getDimensions(), trees));
		newArrayNode.setProperty("initializer", scan(newArrayTree.getInitializers(), trees));
		
		return newArrayNode;
	}

	@Override
	public AstIntermediaryNode<E> visitNewClass(NewClassTree newClassTree, Trees trees) {
		AstIntermediaryNode<E> newClassNode = document.createNode("new");
		setPosition(newClassNode, newClassTree, trees);
		
		newClassNode.setProperty("name", scan(newClassTree.getIdentifier(), trees));
		newClassNode.setProperty("arguments", scan(newClassTree.getArguments(), trees));

		if(newClassTree.getClassBody() != null)
			throw new NotDefinedYetException("Anonymous class body for new class not implemented: " + newClassTree.getClassBody());
		if(newClassTree.getEnclosingExpression() != null)
			throw new NotDefinedYetException("Enclosing expression for new class not implemented: " + newClassTree.getEnclosingExpression());
		if(newClassTree.getTypeArguments() != null && !newClassTree.getTypeArguments().isEmpty()){
			throw new NotDefinedYetException("Type arguments for new class not implemented: " + newClassTree.getTypeArguments());
		}
		return newClassNode;
	}

	@Override
	public AstIntermediaryNode<E> visitOther(Tree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitParameterizedType(ParameterizedTypeTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitParenthesized(ParenthesizedTree parenthesizedTree, Trees trees) {
		AstIntermediaryNode<E> parenthesizedNode = document.createNode("parenthesized");
		setPosition(parenthesizedNode, parenthesizedTree, trees);

		parenthesizedNode.setProperty("body", scan(parenthesizedTree.getExpression(), trees));
		return parenthesizedNode;
	}

	@Override
	public AstIntermediaryNode<E> visitPrimitiveType(PrimitiveTypeTree typeTree, Trees trees) {
		AstIntermediaryNode<E> typeNode = document.createNode("primitive");
		setPosition(typeNode, typeTree, trees);
		
		typeNode.setProperty("value", typeTree.getPrimitiveTypeKind().toString());
		return typeNode;
	}

	@Override
	public AstIntermediaryNode<E> visitReturn(ReturnTree returnTree, Trees trees) {
		AstIntermediaryNode<E> returnNode = document.createNode("return");
		setPosition(returnNode, returnTree, trees);
		returnNode.setProperty("expr", scan(returnTree.getExpression(), trees));
		
		return returnNode;
	}

	@Override
	public AstIntermediaryNode<E> visitSwitch(SwitchTree switchTree, Trees trees) {
		AstIntermediaryNode<E> switchNode = document.createNode("switch");
		setPosition(switchNode, switchTree, trees);

		switchNode.setProperty("expr", scan(switchTree.getExpression(), trees));
		switchNode.setProperty("cases", scan(switchTree.getCases(), trees));

		return switchNode;
	}

	@Override
	public AstIntermediaryNode<E> visitSynchronized(SynchronizedTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitThrow(ThrowTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitTry(TryTree tryTree, Trees trees) {
		// TODO Auto-generated method stub
		throw new NotDefinedYetException(tryTree.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitTypeCast(TypeCastTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitTypeParameter(TypeParameterTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitUnary(UnaryTree unaryTree, Trees trees) {
		AstIntermediaryNode<E> unaryNode = document.createNode("unary");
		setPosition(unaryNode, unaryTree, trees);
		
		unaryNode.setProperty("type", unaryTree.getKind().toString());
		unaryNode.setProperty("expr", scan(unaryTree.getExpression(), trees));
		return unaryNode;
	}

	@Override
	public AstIntermediaryNode<E> visitUnionType(UnionTypeTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitVariable(VariableTree varTree, Trees trees) {
		AstIntermediaryNode<E> varNode = document.createNode("variable");
		setPosition(varNode, varTree, trees);
		
		varNode.setProperty("modifiers", scan(varTree.getModifiers(), trees));
		varNode.setProperty("name", varTree.getName().toString());
		varNode.setProperty("type", scan(varTree.getType(), trees));
		if(varTree.getInitializer() != null)
			varNode.setProperty("initializer", scan(varTree.getInitializer(), trees));

		return varNode;
	}

	@Override
	public AstIntermediaryNode<E> visitWhileLoop(WhileLoopTree whileLoopTree, Trees trees) {
		AstIntermediaryNode<E> whileLoopNode = document.createNode("while_loop");
		setPosition(whileLoopNode, whileLoopTree, trees);
		
		whileLoopNode.setProperty("condition", scan(whileLoopTree.getCondition(), trees));
		whileLoopNode.setProperty("body", scan(whileLoopTree.getStatement(), trees));
		return whileLoopNode;
	}

	@Override
	public AstIntermediaryNode<E> visitWildcard(WildcardTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}
	
	private void setPosition(AstIntermediaryNode<E> node, Tree tree, Trees trees){
		node.setPosition(trees.getSourcePositions().getStartPosition(compilationUnit, tree), 
							trees.getSourcePositions().getEndPosition(compilationUnit, tree));
	}
}
