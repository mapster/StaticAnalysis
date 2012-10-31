package org.mapster.myast;

import java.util.*;

import com.google.gson.*;

public class JsonNode implements AstIntermediaryNode<JsonElement> {
	private static final Set<String> RESERVED_PROPERTY_KEYS = new HashSet<>(Arrays.asList("children", "startPos", "endPos", "NODE_TYPE"));
	private JsonObject element;

	public JsonNode(String nodeType){
		element = new JsonObject();
		element.addProperty("NODE_TYPE", nodeType);
	}


	@Override
	public void setPosition(long start, long end) {
		element.addProperty("startPos", start);
		element.addProperty("endPos", end);
	}

	@Override
	public void addChild(AstIntermediaryNode<JsonElement> node) {
		if(node == null)
			return;
		
		addChild(node.getNode());
	}

	@Override
	public void addChild(JsonElement node) {
		JsonElement children = element.get("children");
		if(children == null){
			element.add("children", node);
		}
		else if(children.isJsonArray()){
			children.getAsJsonArray().add(node);
		}
		else {
			JsonArray array = new JsonArray();
			array.add(children);
			array.add(node);
			element.add("children", array);
		}
	}

	@Override
	public JsonElement getNode() {
		return element;
	}

	@Override
	public void setProperty(String key, AstIntermediaryNode<JsonElement> node) {
		if(node != null && isValidKey(key))
				element.add(key, node.getNode());
	}

	@Override
	public void setProperty(String key, String value) {
		if(value != null && isValidKey(key))
			element.addProperty(key, value);
	}

	private boolean isValidKey(String key) {
		return key != null && !RESERVED_PROPERTY_KEYS.contains(key);
	}

//	@Override
//	public void setModifiers(AstIntermediaryNode<JsonElement> modifiers) {
//		addChild(modifiers);
//	}

//	@Override
//	public void setName(Name name) {
//		element.addProperty("name", name.toString());
//	}
//
//	@Override
//	public void setType(AstIntermediaryNode<JsonElement> type) {
//		addChild(type);
//	}
}
