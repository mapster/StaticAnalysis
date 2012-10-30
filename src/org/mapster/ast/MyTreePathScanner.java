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
	public AstIntermediaryNode<E> visitArrayAccess(ArrayAccessTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitArrayType(ArrayTypeTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		throw new NotDefinedYetException(arg0.getKind().toString());
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
	public AstIntermediaryNode<E> visitBinary(BinaryTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
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
		classNode.setName(classTree.getSimpleName());
		setPosition(classNode, classTree, trees);
		
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
	public AstIntermediaryNode<E> visitExpressionStatement(ExpressionStatementTree arg0,
			Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString() + ": " + arg0.toString());
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
		idNode.setValue(idTree.getName().toString());
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
		litNode.setValue(litTree.getValue() != null ? litTree.getValue().toString() : null);
		
		return litNode;
	}

	@Override
	public AstIntermediaryNode<E> visitMemberSelect(MemberSelectTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitMethod(MethodTree methodTree, Trees trees) {
		AstIntermediaryNode<E> methodNode = document.createNode("method");
		
		//throw exception if defaultValue is set (we don't handle it)
		if(methodTree.getDefaultValue() != null)
			throw new NotDefinedYetException("Method default value action not implemented: " + methodTree.getDefaultValue());

		methodNode.setModifiers(scan(methodTree.getModifiers(), trees));
		methodNode.setName(methodTree.getName());
		methodNode.setType(scan(methodTree.getReturnType(), trees));
		methodTree.getParameters();
		methodTree.getTypeParameters();
		methodNode.addChild(scan(methodTree.getBody(), trees));
		
		return methodNode;
	}
	
	@Override
	public AstIntermediaryNode<E> visitMethodInvocation(MethodInvocationTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitModifiers(ModifiersTree modifiersTree, Trees trees) {
		AstIntermediaryNode<E> modifiersNode = document.createNode("modifiers");
		setPosition(modifiersNode, modifiersTree, trees);
		
		List<String> mods = new LinkedList<>();
		for(Modifier m: modifiersTree.getFlags())
			mods.add(m.toString());
		modifiersNode.setValue(mods);
		
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
			throw new NotDefinedYetException("Anonymous class body not implemented: " + newClassTree.getClassBody());
		if(newClassTree.getEnclosingExpression() != null)
			throw new NotDefinedYetException("Enclosing expression for new class not implemented: " + newClassTree.getEnclosingExpression());
		if(newClassTree.getTypeArguments() != null && !newClassTree.getTypeArguments().isEmpty()){
			throw new NotDefinedYetException("Type arguments not implemented: " + newClassTree.getTypeArguments());
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
		
//		throw new NotDefinedYetException(arg0.getKind().toString());
		return document.createNode("bla");
	}

	@Override
	public AstIntermediaryNode<E> visitParenthesized(ParenthesizedTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitPrimitiveType(PrimitiveTypeTree typeTree, Trees trees) {
		AstIntermediaryNode<E> typeNode = document.createNode("primitive");
		
		typeNode.setValue(typeTree.getPrimitiveTypeKind().toString());
		setPosition(typeNode, typeTree, trees);
		
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
	public AstIntermediaryNode<E> visitUnary(UnaryTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitUnionType(UnionTypeTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
	}

	@Override
	public AstIntermediaryNode<E> visitVariable(VariableTree varTree, Trees trees) {
		AstIntermediaryNode<E> varNode = document.createNode("variable");
		varNode.setName(varTree.getName());
		varNode.setType(scan(varTree.getType(), trees));
		setPosition(varNode, varTree, trees);

		if(varTree.getInitializer() != null)
			varNode.addChild(varTree.getInitializer().accept(this, trees));

		return varNode;
	}

	@Override
	public AstIntermediaryNode<E> visitWhileLoop(WhileLoopTree arg0, Trees arg1) {
		// TODO Auto-generated method stub
		
		throw new NotDefinedYetException(arg0.getKind().toString());
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
