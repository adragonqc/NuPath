package com.example;

/**
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

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Dorm implements Task{


    private User user;
    //private String userName = "demoPerson";
    private int taskNumber = 2;


    public Dorm(User user){
        this.user = user;
    }


    public void completeTask(){

        //Opening database
        MongoClientURI uri = new MongoClientURI("mongodb+srv://nuPathLogin:08426%21%23%25Nnn@nupath.gkq49uo.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);

        //Accessing Tasks database, then getting the DormSelection collection
        MongoDatabase database = mongoClient.getDatabase("Tasks");
        MongoCollection<Document> collection = database.getCollection("DormSelection");

        //Creating new document to insert into the DormSelection collection, so leaderboard can grab the information later

        long now = System.currentTimeMillis();
        Document document = new Document("Username", user.getDisplayName() ).append("Task Number", taskNumber).append("Time", now);
        collection.insertOne(document);

        //Close the mongoClient and prevent this from keeping the connection up to the database
        mongoClient.close();
    }

}