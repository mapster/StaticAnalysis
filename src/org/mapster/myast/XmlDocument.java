package org.mapster.myast;

import java.io.OutputStream;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class XmlDocument implements AstIntermediary<Element> {
	private Document document;
	private XmlNode root;

	public XmlDocument() throws ParserConfigurationException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		this.document = dbf.newDocumentBuilder().newDocument();
		Element rootElement = document.createElement("java-source");
		document.appendChild(rootElement);
		this.root = new XmlNode(rootElement);
	}
	

	@Override
	public void appendToTopLevel(AstIntermediaryNode<Element> node) {
//		root.addChild(node);
	}
	
	@Override
	public AstIntermediaryNode<Element> createNode(String name) {
		return new XmlNode(document.createElement(name));
	}

	@Override
	public void writeToStream(OutputStream os) throws WriteToStreamFailure {
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer trans = tf.newTransformer();
			DOMSource ds = new DOMSource(root.getNode());
			trans.transform(ds, new StreamResult(os));
		} catch (TransformerException e) {
			throw new WriteToStreamFailure(e.getMessage());
		}
	}

}
