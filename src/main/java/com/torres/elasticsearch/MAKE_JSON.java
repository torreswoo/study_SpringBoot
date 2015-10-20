package com.torres.elasticsearch;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.common.xcontent.XContentBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

public class MAKE_JSON {
	
	public static String json() throws IOException{
		
		//1. Do It Yourself
		String json = "{" +
		        "\"user\":\"kimchy\"," +
		        "\"postDate\":\"2013-01-30\"," +
		        "\"message\":\"trying out Elasticsearch\"" +
		    "}";
		
		//2. using Map
		Map<String, Object> json2 = new HashMap<String, Object>();
		json2.put("user","kimchy");
		json2.put("postDate",new Date());
		json2.put("message","trying out Elasticsearch");
		
		
		//3. Serialize beans / Jackson
		ObjectMapper mapper = new ObjectMapper(); // create once, reuse
//		byte[] json3 = mapper.writeValueAsBytes(yourbeaninstance);
		
		//4. ElasticSearch Helper
		XContentBuilder builder = jsonBuilder()
			    .startObject()
			        .field("user", "kimchy")
			        .field("postDate", new Date())
			        .field("message", "trying out Elasticsearch")
			    .endObject();
		String json4 = builder.string();
		
		return json;
	}

}
