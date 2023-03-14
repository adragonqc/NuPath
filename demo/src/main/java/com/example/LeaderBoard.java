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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

        MongoCollection<Document> lbCollection = mongoDB.returnCollection("Tasks", "Leaderboard");

        for(Document doc : lbCollection.find() ){
            if(username.compareTo(doc.getString("Username") ) == 0 && doc.getString(field).compareTo("False") == 0){
                mongoDB.updateDatabase("Tasks", "Leaderboard", username, field, "True");
                int points = Integer.valueOf( doc.getString("Points") );
                points += 100;
                mongoDB.updateDatabase("Tasks", "Leaderboard", username, "Points", String.valueOf(points) );
                //Need to remember to delete the database
            }
            //Do nothing if the if conditions aren't met.
        }

    }

    public ArrayList<ArrayList<String>> returnLists(){
        ArrayList<ArrayList<String>> lbInformation = new ArrayList<>();

        MongoCollection<Document> lbCollection = mongoDB.returnCollection("Tasks", "Leaderboard");

        for(Document doc : lbCollection.find() ){
            ArrayList<String> userPts = new ArrayList<>();
            String username = doc.getString("Username");
            userPts.add(username);
            String strPts = doc.getString("Points");
            //int pts = Integer.valueOf(strPts);
            userPts.add(strPts);
            lbInformation.add( userPts );
        }

        //How to sort a 2d ArrayList Object....
        Collections.sort( lbInformation, new Comparator< ArrayList<String> >() {

            @Override
            public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                return o1.get(1).compareTo( o2.get(1) );
            }
            
        });

        return lbInformation;
    }

}
