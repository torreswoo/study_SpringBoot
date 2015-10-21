package com.torres.elasticsearch;

import java.io.IOException;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;


public class ES_processing {

	
	public void test_processing(){
		
		// 1.접속을 위한 client 생성 
		Settings settings = ImmutableSettings.settingsBuilder()
										  // .put("client.transport.ignore_cluster_name", true)
											 .put("cluster.name", "torresCluster")
											 .build();
										  
		TransportClient client = new TransportClient(settings).addTransportAddress(
								 new InetSocketTransportAddress("localhost", 9300));
		// 2. test APIs
//		test_API_index(client);
//		test_API_Get(client);
//		test_API_Search(client);
//		test_API_MultiSearch(client);
		test_API_Aggregation(client);
		
		
		// 3. client close
		client.close();
	}
	
	
	//...........................................................
	//Client client = buildClient(settings);
	// nodes 처리가
	private Client buildClient(Settings settings) {
		
		TransportClient client = new TransportClient(settings);
		String nodes = "localhost:9300";
//		String nodes = "localhost:9300,...,...";
		String [] nodeList = nodes.split(",");
		
		for(int i = 0 ; i < nodeList.length ; i++){
			client.addTransportAddress(toAddress(nodeList[i]));
		}
		
		return client;
	}

	private TransportAddress toAddress(String address) {
		if( address == null)
			return null;
		
		String [] splitted = address.split(":");
		int port = 9300;
		
		if( splitted.length > 1)
			port = Integer.parseInt(splitted[1]);
			
		return new InetSocketTransportAddress(splitted[0], port);
	}
	//.............................................................
	
	// IndexAPI....................................................
	/*  $ curl -XPOST 'http://localhost:9200/twitter/tweet/' -d '{
    		"user" : "kimchy",
		    "post_date" : "2009-11-15T14:12:12",
		    "message" : "trying out Elasticsearch"
		}'
		$ curl -XPUT 'http://localhost:9200/twitter/tweet/1' -d '{
    		"user" : "kimchy",
		    "post_date" : "2009-11-15T14:12:12",
		    "message" : "trying out Elasticsearch"
		}'
	 */
	public void test_API_index(Client client){
		
		String json = null;
		try {
			json = MAKE_JSON.json();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		IndexResponse response = client.prepareIndex("twitter", "tweet", "1")
		IndexResponse response = client.prepareIndex("twitter", "tweet")
		        .setSource(json)
		        .execute()
		        .actionGet();
		
		// result print
		String _index = response.getIndex(); // Index name
		String _type = response.getType(); // Type name
		String _id = response.getId(); // Document ID (generated or not)
		long _version = response.getVersion();
		boolean created = response.isCreated();
	   	
		System.out.printf("[test_API_index] response : _index(%s), _type(%s), _id(%s), version(), isCreate(%b)\n", _index, _type, _id, created);
	}
	
	// Get API....................................................
	/*
	 * curl -XGET 'http://localhost:9200/twitter/tweet/1'
	 */
	public void test_API_Get(Client client){
		GetResponse response = client.prepareGet("twitter", "tweet", "1")
		        .execute()
		        .actionGet();
		
		// result print
		String _index = response.getIndex(); // Index name
		String _type = response.getType(); // Type name
		String _id = response.getId(); // Document ID (generated or not)
		long _version = response.getVersion();
		boolean isExists = response.isExists();
		
		System.out.printf("[test_API_Get] response : _index(%s), _type(%s), _id(%s), version(), isExists(%b)\n", _index, _type, _id, isExists);
	}
	
	
	//.............................................................

	// Search API..................................................
	
	public void test_API_Search(Client client){
		// match all
		SearchResponse response = client.prepareSearch("twitter")
										.setTypes("tweet")
//										.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
										.setQuery(QueryBuilders.termQuery("user", "kimchy"))
//										.setPostFilter(FilterBuilders.rangeFilter("age").from(12).to(18))
										.execute().actionGet();
		
		// result print
		long totalHits = response.getHits().getTotalHits();
		long took = response.getTookInMillis();

		System.out.printf("[test_API_Search] totalHits(%d), took(%d)\n", totalHits, took);
		Map<String, Object> sampleSource = response.getHits().getAt(0).sourceAsMap(); 	
//		Terms terms = response.getAggregations().get("user");
		System.out.println("[test_API_Search] "+ sampleSource);
//		System.out.println(terms);
	}
	
	// MultiSearch API..................................................
	public void test_API_MultiSearch(Client client){
		SearchRequestBuilder srb1 = client.prepareSearch("twitter")
				  						  .setQuery(QueryBuilders.queryString("elasticsearch"))
				  						  .setSize(1);
		SearchRequestBuilder srb2 = client.prepareSearch()
										  .setQuery(QueryBuilders.matchQuery("user", "kimchy"))
										  .setSize(1);
				  //.setSize(defaultPageSize)
				  //.setExplain(isSetExplain)
				  //.setQuery(QueryBuilders.filteredQuery(QueryBuilders.matchAllQuery(), filterBuilder)
		
		MultiSearchResponse sr = client.prepareMultiSearch()
									   .add(srb1)
									   .add(srb2)
									   .execute().actionGet();
		
		// response
		long nbHits = 0;
		for (MultiSearchResponse.Item item : sr.getResponses()) {
		    SearchResponse response = item.getResponse();
		    nbHits += response.getHits().getTotalHits();
		    System.out.println("[test_API_MultiSearch] "+ nbHits);
		}
		System.out.println("[test_API_MultiSearch] "+ nbHits);
		
	}
	
	// Bulk API..........................................................
	/*
	 * curl -XPOST localhost:9200/cars/transcations/_bulk --data-binary "@carsInfo"
	 */
	
	
	// Aggregations API..................................................

	public void test_API_Aggregation(Client client){
		SearchResponse sr = client.prepareSearch("cars")
								  .setTypes("transactions")
//								  .setQuery(QueryBuilders.matchAllQuery())
								  .addAggregation(AggregationBuilders.terms("colors").field("color")
										  .subAggregation(AggregationBuilders.avg("_inner_avg_price").field("price") )										  )
//								  .addAggregation(AggregationBuilders.stats("statistic").field("postDate"))
								  .addAggregation(AggregationBuilders.avg("avg_price").field("price"))
								  .execute().actionGet();
		System.out.println(sr);
/*		
		// metric
		Stats agg2 = sr.getAggregations().get("agg2");
		double min = agg2.getMin();
		double max = agg2.getMax();
		double avg = agg2.getAvg();
		double sum = agg2.getSum();
		long count = agg2.getCount();
		System.out.println("min : " + min + ", max: "+ max + ", avg : " + avg + ", sum : " + sum + ", count : "+ count);
*/		
		// metrics
		Avg avg_price = sr.getAggregations().get("avg_price");
		double avg = avg_price.getValue();
		
		// buckets
		Terms colors = sr.getAggregations().get("colors");
		for (Terms.Bucket entry : colors.getBuckets()) {
		    String key = entry.getKey();      // Term
		    long docCount = entry.getDocCount(); // Doc count
		    Avg _inner_avg_price = entry.getAggregations().get("_inner_avg_price");
		    double _inner_avg = _inner_avg_price.getValue();
//		    double avg = avg_price.getValue();
		    System.out.println("key : " + key + ", docCount: "+ docCount + ", _inner_avg : " + _inner_avg);
		}
		
	}
	
	
}
