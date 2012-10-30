package org.mapster.myast;

import java.util.List;

import javax.lang.model.element.Name;

import com.google.gson.*;

public class JsonNode implements AstIntermediaryNode<JsonElement> {
	
	private JsonObject element;

	public JsonNode(String nodeType){
		element = new JsonObject();
		element.addProperty("NODE_TYPE", nodeType);
	}

	@Override
	public void setName(Name name) {
		element.addProperty("name", name.toString());
	}

	@Override
	public void setType(AstIntermediaryNode<JsonElement> type) {
		element.add("type", type.getNode());
	}

	@Override
	public void setPosition(long start, long end) {
		element.addProperty("startPos", start);
		element.addProperty("endPos", end);
	}

	@Override
	public void setValue(String value) {
		element.addProperty("value", value);
	}
	
	@Override
	public void setValue(List<String> values){
		JsonArray array = new JsonArray();
		for(String s: values)
			array.add(new JsonPrimitive(s));
		
		element.add("value", array);
	}

	@Override
	public void addChild(AstIntermediaryNode<JsonElement> node) {
		JsonElement children = element.get("value");
		if(children == null){
			element.add("value", node.getNode());
		}
		else if(children.isJsonArray()){
			children.getAsJsonArray().add(node.getNode());
		}
		else {
			JsonArray array = new JsonArray();
			array.add(children);
			array.add(node.getNode());
			element.add("value", array);
		}
	}

	@Override
	public JsonElement getNode() {
		return element;
	}

	@Override
	public void setModifiers(AstIntermediaryNode<JsonElement> modifiers) {
		element.add("modifiers", modifiers.getNode());
	}

}
