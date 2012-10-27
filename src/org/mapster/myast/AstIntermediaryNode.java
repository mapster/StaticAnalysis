package org.mapster.myast;

import javax.lang.model.element.Name;

public interface AstIntermediaryNode<E> {
	public void setName(Name name);
	public void setType(String type);
	public void setPosition(long start, long end);
	public void setValue(String value);
	public void addChild(AstIntermediaryNode<E> node);
	E getNode();
}
