package com.nokia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.yammer.metrics.annotation.Timed;

@Path("/pos-tags")
@Produces(MediaType.APPLICATION_JSON)
public class TaggedResource {
	protected ArkTweetPOSTagger tagger;

	public TaggedResource(ArkTweetPOSTagger tagger) {
		super();
		this.tagger = tagger;
	}

	@GET
	@Timed
	public Map<String, List<TaggedToken>> get(@QueryParam("tweet") String tweet) {
		return getTaggedTweet(tweet);
	}
	@POST
	@Timed
	public Map<String, List<TaggedToken>> post(@FormParam("tweet") String tweet) {
		return getTaggedTweet(tweet);
	}
	
	private	Map<String, List<TaggedToken>> getTaggedTweet(String text) {
		if (text == null) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		Map<String,List<TaggedToken>> taggedTweet = new HashMap<String, List<TaggedToken>>();
		taggedTweet.put("result", tagger.tag(text));
		return taggedTweet;
	}
}
