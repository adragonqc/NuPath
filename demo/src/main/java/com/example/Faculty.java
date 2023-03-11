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





public class Faculty implements Task{

    private ArrayList<String> facultyNames;
    private User user;
    private GetDbCollection mongoDB = new GetDbCollection();


    public Faculty(User user){
        this.user = user;
    }

    public void addFaculty(String faculty){
        this.user.setFacultySelection(faculty);
    }



    public void completeTask(){

        MongoCollection<Document> dormCollection = mongoDB.returnCollection("Tasks", "FacultySelection");
        Document document = new Document("Username", user.getUsername() ).append("Display Name", user.getDisplayName() );
        dormCollection.insertOne(document);
    }

}