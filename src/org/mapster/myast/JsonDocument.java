package org.mapster.myast;

import java.io.*;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;

public class JsonDocument implements AstIntermediary<JsonElement> {
	JsonArray document = new JsonArray();
	
	@Override
	public AstIntermediaryNode<JsonElement> createNode(String name) {
		return new JsonNode(name);
	}

	@Override
	public void appendToTopLevel(AstIntermediaryNode<JsonElement> node) {
		document.add(node.getNode());
	}

	@Override
	public void writeToStream(OutputStream out) throws WriteToStreamFailure {
		try {
			JsonWriter w = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
			Gson gson = new Gson();
			gson.toJson(document, w);
			w.flush();
		} catch (IOException e) {
			throw new WriteToStreamFailure(e.getMessage());
		}
	}

}
