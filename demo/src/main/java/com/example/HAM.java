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

import java.util.ArrayList;



/**
 * Creates new instance of HAM for every user 
 */
public class HAM implements Task{

    private User user;
    private GetDbCollection mongoDB = new GetDbCollection();

    /**
     * Constructor for HAM class
     * @param user - 
     */
    public HAM(User user){
        this.user = user;
    }


    public void addFood(String food){
        this.user.setFoodSelection(food);
    }


    public void completeTask(){

        MongoCollection<Document> foodCollection = mongoDB.returnCollection("Tasks", "FoodSelection");
        Document document = new Document("Username", user.getUsername() ).append("Display Name", user.getDisplayName() );
        foodCollection.insertOne(document);

    }

}
