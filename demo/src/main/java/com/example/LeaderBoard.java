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
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;


/**
 * I can probably save the different leaderboard tasks as .txt files and then every minute or something 
 * like that I can update the lb by reading those .txt files and extracting the necessary information from them 
 */

public class LeaderBoard {
    
    private GetDbCollection mongoDB = new GetDbCollection();

    public LeaderBoard(){

    }


    public void getTasksInformation(){



        MongoCollection<Document> dormCollection = mongoDB.returnCollection("Tasks", "DormSelection");
        MongoCollection<Document> classCollection = mongoDB.returnCollection("Tasks", "ClassSelection");
        MongoCollection<Document> facilitiesCollection = mongoDB.returnCollection("Tasks", "FacilitiesSelection");
        MongoCollection<Document> facultyCollection = mongoDB.returnCollection("Tasks", "FacultySelection");
        MongoCollection<Document> foodCollection = mongoDB.returnCollection("Tasks", "FoodSelection");

        
        for(Document doc : dormCollection.find() ){
            String username = doc.getString("Username");
            updateLeaderBoard(username, "Dorm");
        }

        for(Document doc : classCollection.find() ){
            String username = doc.getString("Username");
            updateLeaderBoard(username, "Class");
        }

        for(Document doc : facilitiesCollection.find() ){
            String username = doc.getString("Username");
            updateLeaderBoard(username, "Facilities");
        }   

        for(Document doc : facultyCollection.find() ){
            String username = doc.getString("Username");
            updateLeaderBoard(username, "Faculty");
        }

        for(Document doc : foodCollection.find() ){
            String username = doc.getString("Username");
            updateLeaderBoard(username, "Food");
        }

    }


    public void updateLeaderBoard(String username, String field){

        //Get pts information

        //Look through leaderboard docs to find user's doc

        //Update the field to true and add + 100 pts
    }


    /**
     * The user doesn't have a leaderboard doc for this... actually I can just initialize this whenever user is created
     */
    public void createNewLBDoc(){

    }


}
