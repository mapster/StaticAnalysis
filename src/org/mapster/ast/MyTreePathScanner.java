package org.mapster.ast;
import java.util.*;

import javax.lang.model.element.Modifier;
import javax.xml.parsers.ParserConfigurationException;

import org.mapster.myast.*;

import com.sun.source.tree.*;
import com.sun.source.util.Trees;


public class MyTreePathScanner<E> implements TreeVisitor<AstIntermediaryNode<E>, Trees> {
	private CompilationUnitTree compilationUnit;
	public final AstIntermediary<E> document;

	public MyTreePathScanner(AstIntermediary<E> document) throws ParserConfigurationException{
		this.document = document;
	}

	public void buildDocument(CompilationUnitTree treeNode, Trees trees) {
		this.compilationUnit = treeNode;
		for(Tree node: treeNode.getTypeDecls()){
			document.appendToTopLevel(node.accept(this, trees));
		}
	}

	public AstIntermediaryNode<E> scan(Tree node, Trees trees){
		return (node == null ? null : node.accept(this, trees));
	}
	
	public List<AstIntermediaryNode<E>> scan(List<? extends Tree> nodes, Trees trees){
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
	public AstIntermediaryNode<E> visitAssert(AssertTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitAssignment(AssignmentTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitBinary(BinaryTree binaryTree, Trees trees) {
		AstIntermediaryNode<E> binaryNode = document.createNode("binary");
		setPosition(binaryNode, binaryTree, trees);
		
		binaryNode.setProperty("left_op", scan(binaryTree.getLeftOperand(), trees));
		binaryNode.setProperty("right_op", scan(binaryTree.getRightOperand(), trees));
		
		return binaryNode;
	}

	@Override
	public AstIntermediaryNode<E> visitBlock(BlockTree blockTree, Trees trees) {
		AstIntermediaryNode<E> blockNode = document.createNode("block");
		setPosition(blockNode, blockTree, trees);

		for(AstIntermediaryNode<E> statement: scan(blockTree.getStatements(), trees)){
			blockNode.addChild(statement);
		}
		
		return blockNode;
	}

	@Override
	public AstIntermediaryNode<E> visitBreak(BreakTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitCase(CaseTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
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
		
		classNode.setProperty("name", classTree.getSimpleName().toString());
		for(AstIntermediaryNode<E> memberNode: scan(classTree.getMembers(), trees)){
			classNode.addChild(memberNode);
		}
		
		return classNode;
	}

	@Override
	public AstIntermediaryNode<E> visitCompilationUnit(CompilationUnitTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitCompoundAssignment(CompoundAssignmentTree arg0,
			Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitConditionalExpression(ConditionalExpressionTree arg0,
			Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitContinue(ContinueTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitDoWhileLoop(DoWhileLoopTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitEmptyStatement(EmptyStatementTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitEnhancedForLoop(EnhancedForLoopTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
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
	public AstIntermediaryNode<E> visitForLoop(ForLoopTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitIdentifier(IdentifierTree idTree, Trees trees) {
		AstIntermediaryNode<E> idNode = document.createNode("identifier");
		setPosition(idNode, idTree, trees);
		
		idNode.setProperty("value", idTree.getName().toString());
		return idNode;
	}

	@Override
	public AstIntermediaryNode<E> visitIf(IfTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitImport(ImportTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
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
			litNode.setProperty("value", litTree.getValue().toString());
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
		
		AstIntermediaryNode<E> parameterNode = document.createNode("parameters");
		for(AstIntermediaryNode<E> node: scan(methodTree.getParameters(), trees)){
			parameterNode.addChild(node);
		}
		methodNode.setProperty("parameters", parameterNode);
		methodNode.setProperty("body", scan(methodTree.getBody(), trees));
		
		return methodNode;
	}
	
	@Override
	public AstIntermediaryNode<E> visitMethodInvocation(MethodInvocationTree methodCallTree, Trees trees) {
		AstIntermediaryNode<E> methodCallNode = document.createNode("method_call");
		setPosition(methodCallNode, methodCallTree, trees);
		
		methodCallNode.setProperty("select", scan(methodCallTree.getMethodSelect(), trees));
		for(AstIntermediaryNode<E> node: scan(methodCallTree.getArguments(), trees)){
			methodCallNode.addChild(node);
		}

		if(methodCallTree.getTypeArguments() != null && !methodCallTree.getTypeArguments().isEmpty())
			throw new NotDefinedYetException("Type arguments for method call not implemented: " + methodCallTree.getTypeArguments());

		return methodCallNode;
	}

	@Override
	public AstIntermediaryNode<E> visitModifiers(ModifiersTree modifiersTree, Trees trees) {
		AstIntermediaryNode<E> modifiersNode = document.createNode("modifiers");
		setPosition(modifiersNode, modifiersTree, trees);
		
		for(Modifier m: modifiersTree.getFlags())
			modifiersNode.addChild(document.createSimpleNode(m.toString()));
		
		return modifiersNode;		
	}

	@Override
	public AstIntermediaryNode<E> visitNewArray(NewArrayTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitNewClass(NewClassTree newClassTree, Trees trees) {
		AstIntermediaryNode<E> newClassNode = document.createNode("new");
		setPosition(newClassNode, newClassTree, trees);
		
		newClassNode.addChild(scan(newClassTree.getIdentifier(), trees));
		for(AstIntermediaryNode<E> arg: scan(newClassTree.getArguments(), trees)){
			newClassNode.addChild(arg);
		}

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
		returnNode.addChild(scan(returnTree.getExpression(), trees));
		
		return returnNode;
	}

	@Override
	public AstIntermediaryNode<E> visitSwitch(SwitchTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
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
	public AstIntermediaryNode<E> visitTry(TryTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
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
		unaryNode.setProperty("value", scan(unaryTree.getExpression(), trees));
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
