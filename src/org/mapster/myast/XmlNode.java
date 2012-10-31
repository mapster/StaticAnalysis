package org.mapster.myast;

import org.w3c.dom.Element;

public class XmlNode implements AstIntermediaryNode<Element> {
	private Element element;

	public XmlNode(Element e) {
		this.element = e;
	}

//	@Override
//	public void setName(Name name) {
//		element.setAttribute("name", name.toString());
//	}
//
//	@Override
//	public void setType(AstIntermediaryNode<Element> type) {
//		if(type != null)
//			element.appendChild(type.getNode());
//	}

	@Override
	public void setPosition(long start, long end) {
		element.setAttribute("startPos", Long.toString(start));
		element.setAttribute("endPos", Long.toString(end));
	}

	@Override
	public void addChild(AstIntermediaryNode<Element> node) {
		if(node != null)
			element.appendChild(node.getNode());
	}

//	@Override
//	public void setValue(String value) {
//		element.setTextContent(value);
//	}

	@Override
	public Element getNode() {
		return element;
	}

	@Override
	public void addChild(Element node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperty(String key, AstIntermediaryNode<Element> node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperty(String key, String value) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void setModifiers(AstIntermediaryNode<Element> modifiers) {
////		throw new Error("not implemented yet");
//	}
//
//	@Override
//	public void setValue(List<String> nodes) {
////		throw new Error("not implemented yet");		
//	}

}
