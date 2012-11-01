package org.mapster.myast;

import java.util.List;


public interface AstIntermediaryNode<E> {
	public void setPosition(long start, long end);
	public void addToProperty(String key, AstIntermediaryNode<E> value);
	public void addToProperty(String key, String value);
	public void setProperty(String key, AstIntermediaryNode<E> node);
	public void setProperty(String key, String value);
	public void setProperty(String key, List<AstIntermediaryNode<E>> values);
	E getNode();
}
