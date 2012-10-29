package org.mapster.myast;

import java.util.List;

import javax.lang.model.element.Name;

public interface AstIntermediaryNode<E> {
	public void setName(Name name);
	public void setModifiers(AstIntermediaryNode<E> modifiers);
	public void setType(String type);
	public void setPosition(long start, long end);
	public void setValue(String value);
	public void setValue(List<String> nodes);
	public void addChild(AstIntermediaryNode<E> node);
	E getNode();
}
