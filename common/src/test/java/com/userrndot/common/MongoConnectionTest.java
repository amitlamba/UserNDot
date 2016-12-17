package com.userrndot.common;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.MongoClient;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/common-context.xml" })
public class MongoConnectionTest {
	
	@Resource
	MongoClient mongo;

	@Test
	public void test() {
//		MongoClient mongo = new MongoClient("127.0.0.1");
		 MongoDatabase database = mongo.getDatabase("test");
		 System.out.println(database.getName());
		 MongoIterable<String> listCollectionNames = database.listCollectionNames();
		 for (String string : listCollectionNames) {
			System.out.println(string);
		}
		 Map<String, Object> documentMap = new HashMap<String, Object>();
		 documentMap.put("key", "value");
		 
		 System.out.println(database.getCollection("samplecollection").count());
		 mongo.close();
		fail("Not yet implemented");
	}

}
