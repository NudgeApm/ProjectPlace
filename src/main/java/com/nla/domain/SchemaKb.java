package com.nla.domain;

public class SchemaKb extends Schema implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SchemaKb(String name) {
		super(name);
	}

	@Override
	public String getType() {
		return "KnowledgeBase";
	}
}
