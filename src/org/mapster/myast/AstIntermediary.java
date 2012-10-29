package org.mapster.myast;

import java.io.OutputStream;

public interface AstIntermediary<E> {
	public AstIntermediaryNode<E> createNode(String name);
//	public AstIntermediaryNode<E> createList();
	public void appendToTopLevel(AstIntermediaryNode<E> node);
	public void writeToStream(OutputStream os) throws WriteToStreamFailure;
}
