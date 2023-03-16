package com.example;

/**
 * 
 * @Author: Andrew Skevington-Olivera
 * @Date: 6-3-2023
 */

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

public class saveToMongo {
    
    public void testMongo(){
       
        ArrayList<String> test = new ArrayList<>();

        MongoClientURI uri = new MongoClientURI("mongodb+srv://nuPathLogin:08426%21%23%25Nnn@nupath.gkq49uo.mongodb.net/test");
        
        MongoClient mongoClient = new MongoClient(uri);

      
        // Accessing the database 
        MongoDatabase database = mongoClient.getDatabase("test"); 
        //System.out.println(database.getCollection("test1") ); 
        //collection.insertOne(document)
        

        DBObject obj = new BasicDBObject("employee", "bob");
        MongoCollection<Document> collection = database.getCollection("test");
        Document document = new Document("name", "iPhone");
        Document query = new Document("_id", new ObjectId("64062ad955fc570c4b5c14eb") );
        Document result = collection.find(query).iterator().next();

        System.out.println(result.getString("test3") );
        

        mongoClient.close();
        

    }
    
    

}
