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
    

    public LeaderBoard(){

    }


    public void getTasksInformation(){

        //Opening database
        MongoClientURI uri = new MongoClientURI("mongodb+srv://nuPathLogin:08426%21%23%25Nnn@nupath.gkq49uo.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);

        //Accessing Tasks database, then getting all of the collections within it
        MongoDatabase database = mongoClient.getDatabase("Tasks");
        MongoCollection<Document> dormCollection = database.getCollection("DormSelection");
        MongoCollection<Document> classCollection = database.getCollection("ClassSelection");
        MongoCollection<Document> facilitiesCollection = database.getCollection("FacilitiesSelection");
        MongoCollection<Document> facultyCollection = database.getCollection("FacultySelection");
        MongoCollection<Document> foodCollection = database.getCollection("FoodSelection");

        FindIterable<Document> dormIterDoc = dormCollection.find();
        Iterator it = dormIterDoc.iterator();

        /** 
        int i = 1;
        while(it.hasNext() ){
            System.out.println( it.next() );
            
            i++;
        }*/


        ArrayList<String> dormInformation = new ArrayList<>();
        ArrayList<Integer> dormInformationTask = new ArrayList<>();

        for(Document document : dormCollection.find() ){
            System.out.println(document.getString("Username") );
            dormInformation.add( document.getString("Username") );
            dormInformationTask.add( document.getInteger("Task Number") );
        }

        for(int i = 0; i < dormInformation.size(); i++){
            System.out.println(dormInformation.get(i) );
        }

        String test = "";
        for(Document document : foodCollection.find() ){
            test = document.getString("Username") ;
        }
        



        //If ArrayList.contains("StringToBeChecked");
        //If list.contains(testString); returns boolean
        


        //String value = dormCollection.find().projection(Projections.include("Username") ).first().getString("Username");
        //System.out.println(value);

        //Close the mongoClient and prevent this from keeping the connection up to the database
        mongoClient.close();
    }


    public void addToLB(String name, int taskNumber){


        /**
        * Display Name
        * Username (Hidden) 
        * Dorm task status
        * HAM task status
        * Classes task status
        * Faculty task status
        * Facilities task status
        */

        //Opening database
        MongoClientURI uri = new MongoClientURI("mongodb+srv://nuPathLogin:08426%21%23%25Nnn@nupath.gkq49uo.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);

        //Accessing Tasks database, then getting all of the collections within it
        MongoDatabase database = mongoClient.getDatabase("Tasks");
        MongoCollection<Document> leaderboardCollection = database.getCollection("Leaderboard");

        for(Document document : leaderboardCollection.find() ){
            if( name.compareTo(document.getString("Username")) == 0) ;
        }
    }


}
