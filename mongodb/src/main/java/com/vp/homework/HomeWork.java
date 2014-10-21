package com.vp.homework;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class HomeWork {
	private static MongoClient mc;
	static{
		mc= getMongoClient();
	}
	

	public static MongoClient getMongoClient()
	{
		MongoClient mongoClient=null;
		try {
			mongoClient= new MongoClient();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//by default to 
		if (mongoClient==null) {
			throw new NullPointerException();
		}
		return mongoClient;
	}
	
	public static DB getDB(String db){
		return mc.getDB(db);
	}
	
}
