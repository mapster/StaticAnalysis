package org.mapster.myast;

import java.util.Set;

import javax.lang.model.element.*;

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
	public void setType(String type) {
		element.addProperty("type", type);
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
	public void setModifiers(Set<Modifier> modifiers) {
		JsonArray array = new JsonArray();
		for(Modifier m: modifiers)
			array.add(new JsonPrimitive(m.toString()));
		element.add("modifiers", array);
	}

}
