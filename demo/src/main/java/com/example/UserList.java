package com.example;

/*
 * 
 * @Author: Andrew-Skevington-Olivera
 * @Date: 4-3-2023
 */


//Do I even need this when I can access users one by one and its done by login information?
//Maybe to save all of them but I can do that when user has any change....
//Aaah this could prob be used for multiple users interacting w/ the website at once ? Yeee
//has all the users that're currently up, then displays only this user in the list to them
//and one main section instead of having it go one by one in the main class

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

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
import com.mongodb.client.model.Updates;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

public class UserList {
    
    private static UserList instance = new UserList();
    private ArrayList<User> userArray = new ArrayList<>();
    private GetDbCollection mongoDB = new GetDbCollection();
    private HAM ham;
    private Facilities facilities;
    private Faculty faculty;
    private Classes classes;
    private Dorm dorm;
    private Forums forum = new Forums();

    public UserList(){

    }

    public void checkLogin(String username, String pw){


        MongoCollection<Document> userCollection = mongoDB.returnCollection("UserDatabase", "Users");
        
        String newUsername = null;
        String password = null;

        for(Document doc : userCollection.find() ){

            if( (username.compareTo( doc.getString("Username") ) == 0) && (pw.compareTo( doc.getString("Password") ) == 0) ){
                newUsername = doc.getString("Username");
                password = doc.getString("Password");
                break;
            }
        }

        if(newUsername != null && password != null){
            User oldUser = new User(newUsername, password);
            addUser(oldUser);
        }
        else{
            System.out.println("The login information is incorrect, please try again");
        }
    }


    public void createUser(String displayName, String username, String password, String contactInfo){
        
        MongoCollection<Document> userCollection = mongoDB.returnCollection("UserDatabase", "Users");

        boolean ifUser = false;
        for(Document doc : userCollection.find() ){

            if( username.compareTo( doc.getString("Username") ) == 0){
                ifUser = true;
            }
        }

        if(ifUser){
            System.out.println("This username is already taken");
        }
        else{
            User newUser = new User(displayName, username, password, contactInfo);
            addUser( newUser );
        }   

    }


    private void addUser(User user){
        userArray.add(user);
    }

    public void removeUser(User user){
        userArray.remove(user);
    }

    public User accessUser(String userName){

        for(User user : this.userArray){
            if( userName.compareTo( user.getUsername() ) == 0){
                return user; //Breaks out of for loop
            }
        }

        //User should be found, so this line should never run
        return null;
    }

    /**
     * This is for forums if you want to see all of the online users.
     * @return all of the user in the User ArrayList
     */
    public ArrayList<User> getUsersArray(){
        return userArray;
    }

    public static UserList getInstance(){
        return instance;
    }

    public Forums getForum(){
        return this.forum;
    }
    
}
