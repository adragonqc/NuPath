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


public class Classes implements Task{
    
    ArrayList<Class> classList;
    private User user;
    private String userName = "demoPerson";
    private int taskNumber = 1;

    public Classes(){

    }
    
    public void addClasses(Class class1){
        classList.add(class1);
    }


    //This'll somehow convert the information in Class to be added to the Calendar 
    //Prob use how events were done in project 1 but using Days enums instead to keep
    //adding classes throughout a time period
    public void addClassesToCalendar(){

    }


    public void completeTask(){

        //Opening database
        MongoClientURI uri = new MongoClientURI("mongodb+srv://nuPathLogin:08426%21%23%25Nnn@nupath.gkq49uo.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);

        //Accessing Tasks database, then getting the ClassSelection collection
        MongoDatabase database = mongoClient.getDatabase("Tasks");
        MongoCollection<Document> collection = database.getCollection("ClassSelection");

        //Creating new document to insert into the ClassSelection collection, so leaderboard can grab the information later
        Document document = new Document("Username", userName).append("Task Number", taskNumber);
        collection.insertOne(document);

        //Close the mongoClient and prevent this from keeping the connection up to the database
        mongoClient.close();
      
    }


}
