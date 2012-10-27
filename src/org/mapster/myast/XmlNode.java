package org.mapster.myast;

import javax.lang.model.element.Name;

import org.w3c.dom.Element;

public class XmlNode implements AstIntermediaryNode<Element> {
	private Element element;

	public XmlNode(Element e) {
		this.element = e;
	}

	@Override
	public void setName(Name name) {
		element.setAttribute("name", name.toString());
	}

	@Override
	public void setType(String type) {
		element.setAttribute("type", type);
	}

	@Override
	public void setPosition(long start, long end) {
		element.setAttribute("startPos", Long.toString(start));
		element.setAttribute("endPos", Long.toString(end));
	}

	@Override
	public void addChild(AstIntermediaryNode<Element> node) {
		element.appendChild(node.getNode());
	}

	@Override
	public void setValue(String value) {
		element.setTextContent(value);
	}

	@Override
	public Element getNode() {
		return element;
	}

}
