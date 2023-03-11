package com.example;






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



public class GetDbCollection {
    
    public GetDbCollection(){

    }

    public MongoCollection<Document> returnCollection(String db, String collection){

        MongoClientURI uri = new MongoClientURI("mongodb+srv://nuPathLogin:08426%21%23%25Nnn@nupath.gkq49uo.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);

        //Accessing the database, then accessing the collection
        MongoDatabase database = mongoClient.getDatabase(db);

        return database.getCollection(collection);
    }

    public void updateDatabase(String database, String collectionName, String username, String field, String updatedVar){


        MongoCollection<Document> collection = returnCollection(database, collectionName);

        for(Document doc: collection.find() ){
            if( username.compareTo( doc.getString("Username") ) == 0){
                String oldVar = doc.getString(field);
                Document query = new Document(field, oldVar);
                Bson updates = Updates.combine(Updates.set(field, updatedVar) );
                collection.updateOne(query, updates);
                break;
            }
        }
        
    }
}
