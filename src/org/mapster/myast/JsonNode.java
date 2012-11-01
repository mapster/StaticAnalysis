package org.mapster.myast;

import java.util.*;

import com.google.gson.*;

public class JsonNode implements AstIntermediaryNode<JsonElement> {
	private static final Set<String> RESERVED_PROPERTY_KEYS = new HashSet<>(Arrays.asList("startPos", "endPos", "NODE_TYPE"));
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

	private void addToProperty(String key, JsonElement value) {
		if(value == null || !isValidKey(key))
			return;
		
		JsonElement property = element.get(key);
		if(property == null){
			element.add(key, value);
		}
		else if(property.isJsonArray()){
			property.getAsJsonArray().add(value);
		}
		else {
			JsonArray array = new JsonArray();
			array.add(property);
			array.add(value);
			element.add(key, array);
		}
	}

	@Override
	public void addToProperty(String key, AstIntermediaryNode<JsonElement> value) {
		if(value == null)
			return;
		
		addToProperty(key, value.getNode());
	}
	
	@Override
	public void addToProperty(String key, String value) {
		addToProperty(key, new JsonPrimitive(value));
	}

	@Override
	public JsonElement getNode() {
		return element;
	}
	
	@Override
	public void setProperty(String key, List<AstIntermediaryNode<JsonElement>> values){
		if(values == null || !isValidKey(key))
			return;
		
		//clear whatever is stored on that key
		element.add(key, new JsonArray());
		for(AstIntermediaryNode<JsonElement> val: values)
			addToProperty(key, val);
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
}
