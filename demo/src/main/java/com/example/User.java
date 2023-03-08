package com.example;

/**
 * 
 * @Author: Andrew-Skevington-Olivera
 * @Date: 4-3-2023
 */


import java.util.ArrayList;



/**
 * TO-DO:
 * Save/Get Name
 * Save/Get Username
 * Save/Get Password
 * Save/Get Classes
 * Save/Get Faculty
 * Get posts
 * Save/Get Food Options
 * Save/Get Dorm Selection
 * Save/Get About me
 * (OPT) Save/Get Doodles
 * Save/Get PFP
 */


//If user is null then don't access certain things like adding classes,food options, etc
//Permission of 0+ to view certain stuff, Permission of 1+ to view forums, Permission of 2+ to add classes
//Basically visitor, student, admin
//Whole thing is dumb, only 2 permissions are needed and if user is null then it can't view things like forums


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


public class User {
    
    private String displayName, username, password, socialMedia, interests, foodSelections, facultySelections, facilitiesSlection, dormSelection, 
    aboutMe;
    private int permissionLevel;
    private Classes classes;
    //Have img PFP;
    //Also have img[] photoGallery


    public User(String displayName, String username, String password, String socialMedia, String interests, String aboutMe){

        this.displayName = displayName;
        this.username = username;
        this.password = password;
        this.socialMedia = socialMedia;
        this.interests = interests;
        this.aboutMe = aboutMe;
        this.permissionLevel = 1;
    }



    public void updateDisplayName(String name){
        this.displayName = name;
        saveInformation();
    }

    public void updatePassword(String password){
        this.password = password;
        saveInformation();
    }

    public void updateAboutMe(String aboutMe){
        this.aboutMe = aboutMe;
        saveInformation();
    }

    public void setFoodSelection(String foodSelection){
        this.foodSelections = foodSelection;
    }

    public void setFacultySelection(String facultySelection){
        this.facultySelections = facultySelection;
    }

    public void setFacilitiesSelection(String facilitiesSelection){
        this.facilitiesSlection = facilitiesSelection;
    }

    public void setDormSelection(String dormSelection){
        this.dormSelection = dormSelection;
    }

    public void setClasses(Class selectedClass){
        this.classes.addClasses(selectedClass);
    }

    

    public void updatePermissionLevel(int permissionLevel){
        this.permissionLevel = permissionLevel;
    }

    public String getDisplayName(){
        return displayName;
    }

    public String returnAboutMe(){
        return aboutMe;
    }






    public boolean checkLoginInformation(String username, String password){

        if(this.username == username && this.password == password){
            return true;
        }
        return false;
    }


    public void saveInformation(){

        //Opening database
        MongoClientURI uri = new MongoClientURI("mongodb+srv://nuPathLogin:08426%21%23%25Nnn@nupath.gkq49uo.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);

        //Accessing UserDatabase database, then getting the Users collection
        MongoDatabase database = mongoClient.getDatabase("UserDatabase");
        MongoCollection<Document> collection = database.getCollection("Users");

        //Creating new document to insert into the Users collection, so leaderboard can grab the information later
        Document document = new Document("Username", username).append("Password", password).append("Display Name", displayName)
        .append("Social Media", socialMedia).append("Interests", interests).append("Food Selection", foodSelections)
        .append("Faculty Selection", facultySelections).append("Facilities Selection", facilitiesSlection).append("Dorm Selection", dormSelection)
        .append("About Me", aboutMe);

        collection.insertOne(document);

        //Close the mongoClient and prevent this from keeping the connection up to the database
        mongoClient.close();
    }

    public void accessUserInformation(){
        
    }


}
