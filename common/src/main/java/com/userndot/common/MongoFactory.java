package com.userndot.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

public class MongoFactory {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String ip;
    private MongoClientOptions mongoOptions;


    public MongoClientOptions getMongoOptions() {
        return mongoOptions;
    }

    public String getIp() {
        return ip;
    }


    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setMongoOptions(MongoClientOptions mongoOptions) {
        this.mongoOptions = mongoOptions;
    }


    /**
     * Builder to create mongo instance
     * 
     * @return
     */
    public MongoClient buildMongo() {
            return new MongoClient(ip, mongoOptions);
    }
}
