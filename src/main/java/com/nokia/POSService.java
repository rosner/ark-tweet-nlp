package com.nokia;

import cmu.arktweetnlp.impl.Model;
import cmu.arktweetnlp.impl.features.FeatureExtractor;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class POSService extends Service<ArkTweetNLPConfiguration> {
	
	@Override
	public void initialize(Bootstrap<ArkTweetNLPConfiguration> bootstrap) {
		bootstrap.setName("ark-tweet-tagger");
	}

	@Override
	public void run(ArkTweetNLPConfiguration configuration, Environment environment) throws Exception {
		Model model = Model.loadModelFromText(configuration.getModelPath());
		FeatureExtractor featureExtractor = new FeatureExtractor(model, false);
		environment.addResource(new TaggedResource(new ArkTweetPOSTagger(model, featureExtractor)));
	}
	
	public static void main(String[] args) throws Exception {
        new POSService().run(args);
    }

}
