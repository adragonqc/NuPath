package com.example;

/**
 * This is to update ClassSelection collection, so that leaderboard can collect the information to update
 * leaderboard collection with.
 * @Author: Andrew-Skevington-Olivera
 * @Date: 4-3-2023
 */





import com.mongodb.client.MongoCollection;

import org.bson.Document;


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


    public void completeTask(){

        MongoCollection<Document> classCollection = mongoDB.returnCollection("Tasks", "ClassSelection");
        Document document = new Document("Username", user.getUsername() ).append("Display Name", user.getDisplayName() );
        classCollection.insertOne(document);
      
    }


}
