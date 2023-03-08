package com.example;

/**
 * 
 * @Author: Andrew-Skevington-Olivera
 * @Date: 4-3-2023
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



public class Facilities implements Task{

    private User user;
    private String userName = "demoPerson";
    private int taskNumber = 4;

    public Facilities(){
        
    }


    public void completeTask(){

       //Opening database
       MongoClientURI uri = new MongoClientURI("mongodb+srv://nuPathLogin:08426%21%23%25Nnn@nupath.gkq49uo.mongodb.net/test");
       MongoClient mongoClient = new MongoClient(uri);

       //Accessing Tasks database, then getting the FacilitiesSelection collection
       MongoDatabase database = mongoClient.getDatabase("Tasks");
       MongoCollection<Document> collection = database.getCollection("FacilitiesSelection");

       //Creating new document to insert into the FacilitiesSelection collection, so leaderboard can grab the information later
       Document document = new Document("Username", userName).append("Task Number", taskNumber);
       collection.insertOne(document);

       //Close the mongoClient and prevent this from keeping the connection up to the database
       mongoClient.close();
    }


}
