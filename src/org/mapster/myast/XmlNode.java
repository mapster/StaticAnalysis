package org.mapster.myast;

import java.util.List;

import org.w3c.dom.Element;

public class XmlNode implements AstIntermediaryNode<Element> {
	private Element element;

	public XmlNode(Element e) {
		this.element = e;
	}

	@Override
	public void setPosition(long start, long end) {
		element.setAttribute("startPos", Long.toString(start));
		element.setAttribute("endPos", Long.toString(end));
	}

	@Override
	public void addToProperty(String key, AstIntermediaryNode<Element> value) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addToProperty(String key, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperty(String key, AstIntermediaryNode<Element> node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperty(String key, String value) {
		
	}

	@Override
	public void setProperty(String key,
			List<AstIntermediaryNode<Element>> values) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Element getNode() {
		return element;
	}

	
}
