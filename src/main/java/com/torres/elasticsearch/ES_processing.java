package com.torres.elasticsearch;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import java.io.IOException;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.node.Node;


public class ES_processing {

	
	public void test_processing(){
		
		// 1.접속을 위한 client 생성 
		Settings settings = ImmutableSettings.settingsBuilder()
										  // .put("client.transport.ignore_cluster_name", true)
											 .put("cluster.name", "torresCluster")
											 .build();
										  
		TransportClient client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
		
		
		// 2. test APIs
		test_API_index(client);
		test_API_Get(client);
		
		
		
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

}
