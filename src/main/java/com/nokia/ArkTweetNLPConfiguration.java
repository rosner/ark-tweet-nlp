package com.nokia;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

public class ArkTweetNLPConfiguration extends Configuration {

	@JsonProperty
	private String modelPath = "/cmu/arktweetnlp/oct27.model";

	public String getModelPath() {
		return modelPath;
	}

}
