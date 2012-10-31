package org.mapster.myast;


public interface AstIntermediaryNode<E> {
//	public void setName(Name name);
//	public void setModifiers(AstIntermediaryNode<E> modifiers);
//	public void setType(AstIntermediaryNode<E> type);
	public void setPosition(long start, long end);
//	public void setValue(String value);
//	public void setValue(List<String> nodes);
	public void addChild(AstIntermediaryNode<E> node);
	public void addChild(E node);
	public void setProperty(String key, AstIntermediaryNode<E> node);
	public void setProperty(String key, String value);
	E getNode();
}
