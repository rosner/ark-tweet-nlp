package com.nokia;

import java.util.ArrayList;
import java.util.List;

import cmu.arktweetnlp.Twokenize;
import cmu.arktweetnlp.impl.Model;
import cmu.arktweetnlp.impl.ModelSentence;
import cmu.arktweetnlp.impl.Sentence;
import cmu.arktweetnlp.impl.features.FeatureExtractor;

public class ArkTweetPOSTagger {

	private Model model;
	private FeatureExtractor featureExtractor;
	
	public ArkTweetPOSTagger(Model model, FeatureExtractor featureExtractor) {
		super();
		this.model = model;
		this.featureExtractor = featureExtractor;
	}
	
	public List<TaggedToken> tag(String text) {
		List<String> tokens = Twokenize.tokenizeRawTweetText(text);

		Sentence sentence = new Sentence();
		sentence.tokens = tokens;
		int sentenceSize = sentence.tokens.size();
		ModelSentence modelSentence = new ModelSentence(sentenceSize);
		featureExtractor.computeFeatures(sentence, modelSentence);
		model.greedyDecode(modelSentence, false);

		List<TaggedToken> taggedTokens = new ArrayList<TaggedToken>();

		for (int tokenIndex = 0; tokenIndex < sentenceSize; tokenIndex++) {
			String currentToken = tokens.get(tokenIndex);
			String currentTag = model.labelVocab.name(modelSentence.labels[tokenIndex]);
			TaggedToken taggedToken = new com.nokia.TaggedToken(currentTag, currentToken);
			taggedTokens.add(taggedToken);
		}

		return taggedTokens;

	}	
}
