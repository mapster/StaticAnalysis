package org.mapster.ast;

public class NotDefinedYetException extends Error {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7881096528564952677L;


	public NotDefinedYetException(String msg) {
		super(msg);
	}
	
	public String toString(){
		return "Doesn't support transformation of "+this.getMessage()+" nodes yet.";
	}
}
