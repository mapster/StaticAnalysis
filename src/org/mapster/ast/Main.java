package org.mapster.ast;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.*;


public class Main {

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://127.0.0.1/").port(9998).build();
	}
	
	private static final URI BASE_URI = getBaseURI();

	public static void main(String[] args) throws IOException {
		HttpServer httpServer = startServer();
		System.in.read();
		httpServer.stop();
	}
	
	protected static HttpServer startServer() throws IOException{
		System.out.println("Starting grizzly...");
		ResourceConfig rc = new PackagesResourceConfig("org.mapster.ast");
		return GrizzlyServerFactory.createHttpServer(BASE_URI, rc);
	}
}
