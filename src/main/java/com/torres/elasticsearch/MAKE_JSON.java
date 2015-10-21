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

/* Bulk API
curl -XPOST localhost:9200/cars/transcations/_bulk --data-binary "@carsInfo"

{ "index" : {}}
{ "price" : 10000, "color" : "red", "make" : "honda", "sold" : "2014-10-28"}
{ "index" : {}}
{ "price" : 20000, "color" : "red", "make" : "honda", "sold" : "2014-11-05"}
{ "index" : {}}
{ "price" : 30000, "color" : "green", "make" : "ford", "sold" : "2014-05-18"}
{ "index" : {}}
{ "price" : 15000, "color" : "blue", "make" : "toyota", "sold" : "2014-07-02"}
{ "index" : {}}
{ "price" : 12000, "color" : "red", "make" : "honda", "sold" : "2014-10-28"}
{ "index" : {}}
{ "price" : 18000, "color" : "blue", "make" : "honda", "sold" : "2014-11-28"}
{ "index" : {}}
{ "price" : 80000, "color" : "green", "make" : "ford", "sold" : "2014-12-25"}
{ "index" : {}}
{ "price" : 25000, "color" : "blue", "make" : "honda", "sold" : "2014-10-05"}
{ "index" : {}}
{ "price" : 35000, "color" : "red", "make" : "ford", "sold" : "2014-10-28"}
 
 */


/*
/* Query DSL
		GET /cars/transactions/_search?search_type=count
		{
		    "aggs" : {
		        "colors" : {
		            "terms": {
		                "field" : "color"            
		            }        
		        }        
		    }
		}
		
		
		GET /cars/transactions/_search?search_type=count
		{
		    "aggs" : {
		        "colors" : {
		            "terms": {
		                "field" : "color"            
		            },
		            "aggs" : {
		                "avg_price" : {
		                    "avg" : {
		                        "field" : "price"
		                    }
		                }        
		            }        
		        }
		    }
		}
	 
	
	
	
	/* SQL
	 * select color, count(*) as colors from cars group by color
	 */


 * /
