package com.nokia;

public class TaggedToken {

	protected String tag;
	protected String token;

	public TaggedToken(String tag, String token) {
		super();
		this.tag = tag;
		this.token = token;
	}

	public String getTag() {
		return tag;
	}

	public String getToken() {
		return token;
	}

}
