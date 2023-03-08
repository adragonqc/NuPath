package com.example;

/**
 * 
 * @Author: Andrew Skevington-Olivera
 * @Date: 8-3-2023
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

public class Forums {
    
    ArrayList<User> userList;
    //User testUser = userList.get(0);
    
    public Forums(){

    }


    public void updateUserList(ArrayList<User> updatedList){
        this.userList = new ArrayList<>();
        for(int i = 0; i < updatedList.size(); i++){
            userList.add( updatedList.get(i) );
        }
    }

    public ArrayList<User> returnUsers(){
        return userList;
    }

    public void addMessage(String message){

        //Opening database
        MongoClientURI uri = new MongoClientURI("mongodb+srv://nuPathLogin:08426%21%23%25Nnn@nupath.gkq49uo.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);

        //Accessing Forum database, then getting the Messages collection
        MongoDatabase database = mongoClient.getDatabase("Forum");
        MongoCollection<Document> collection = database.getCollection("Messages");

        //Creating new document to insert into the Message collection, so leaderboard can grab the information later
        long now = System.currentTimeMillis();
        Document document = new Document("Display Name", userList.get(0).getDisplayName() ).append("Message", message).append("Time", now);
        collection.insertOne(document);

        //Close the mongoClient and prevent this from keeping the connection up to the database
        mongoClient.close();
    }

    public void displayMessages(){

    }

}
