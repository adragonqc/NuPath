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
    private GetDbCollection mongoDB = new GetDbCollection();

    public Facilities(User user){
        this.user = user;
    }

    public void addFacilities(String interests){
        this.user.setFacilitiesSelection(interests);
    }


    public void completeTask(){

       MongoCollection<Document> facilitiesCollection = mongoDB.returnCollection("Tasks", "FacilitiesSelection");
       Document document = new Document("Username", user.getUsername() ).append("Display Name", user.getDisplayName() );
       facilitiesCollection.insertOne(document);

    }


}
