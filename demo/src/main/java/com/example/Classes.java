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
    
    private String classSelected;
    private User user;
    private GetDbCollection mongoDB = new GetDbCollection();

    public Classes(User user){
        this.user = user;
    }
    
    public void addClasses(String classPicked){
        this.classSelected = classPicked;
    }


    //This'll somehow convert the information in Class to be added to the Calendar 
    //Prob use how events were done in project 1 but using Days enums instead to keep
    //adding classes throughout a time period
    public void addClassesToCalendar(){

    }


    public void completeTask(){

        MongoCollection<Document> classCollection = mongoDB.returnCollection("Tasks", "ClassSelection");
        Document document = new Document("Username", user.getUsername() ).append("Display Name", user.getDisplayName() );
        classCollection.insertOne(document);
      
    }


}
