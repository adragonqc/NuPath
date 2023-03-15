package com.example;

/**
 * 
 * @Author: Andrew-Skevington-Olivera
 * @Date: 4-3-2023
 */


import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.io.FileUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.awt.*;


/**
 * 
 */
public class User {
    
    private String displayName, username, password, interests, foodSelections, facultySelections, facilitiesSlection, dormSelection, 
    aboutMe, pfpString, permissionLevel, contactInformation;
    private Classes classes;
    private BufferedImage pfp;
    private List<Object> photoGallery = new ArrayList<>();
    private GetDbCollection mongoDB = new GetDbCollection();


    /**
     * This is the constructor for when a user is first making their account
     * @param displayName
     * @param username
     * @param password
     */
    public User(String displayName, String username, String password, String contactInformation){

        this.displayName = displayName;
        this.username = username;
        this.password = password;
        this.contactInformation = contactInformation;
        this.permissionLevel = "1";

        saveInformation();  //Only saves information when User is initialized
        MongoCollection<Document> leaderboard = mongoDB.returnCollection("Tasks", "Leaderboard");
        Document document = new Document("Points", "0").append("Display Name", this.displayName).append("Username", this.username)
        .append("Dorm", "False").append("Class", "False").append("Facilities", "False")
        .append("Faculty", "False").append("Food", "False");
        leaderboard.insertOne(document);
    }


   /**
    * 
    * @param username
    * @param password
    */
    public User(String username, String password){
        accessUserInformation(username);
    }


    public void updateDisplayName(String name){
        this.displayName = name;
        mongoDB.updateDatabase("UserDatabase", "Users", this.username, "Display Name", this.displayName);
    }

    public void updatePassword(String password){
        this.password = password;
        mongoDB.updateDatabase("UserDatabase", "Users", this.username, "Password", this.password);
    }

    public void uploadPFP(String fileName){
        this.pfpString = fileName;
        mongoDB.updateDatabase("UserDatabase", "Users", this.username, "PFP", this.pfpString);
    }

    public void updateContactInfo(String contactInfo){
        this.contactInformation = contactInfo;
        mongoDB.updateDatabase("UserDatabase", "Users", this.username, "Contact Information", this.contactInformation);
    }

    public void addImgToPhotos(String fileName){

        this.photoGallery.add( fileName );


        MongoCollection<Document> collection = mongoDB.returnCollection("UserDatabase", "Users");

        for(Document doc: collection.find() ){
            if( username.compareTo( doc.getString("Username") ) == 0){
                String oldVar = doc.getString("Photo Gallery");
                Document query = new Document("Photo Gallery", oldVar);
                Bson updates = Updates.combine(Updates.set("Photo Gallery", photoGallery) );
                collection.updateOne(query, updates);
                break;
            }
        }
    }

    public void updateInterests(String interestss){
        this.interests = interestss;
        System.out.println();
        System.out.println("This is the username that is going through and being updated " + this.username );
        System.out.println();
        mongoDB.updateDatabase("UserDatabase", "Users", this.username, "Interests", this.interests);
    }

    public void updateAboutMe(String aboutMe){
        this.aboutMe = aboutMe;
        mongoDB.updateDatabase("UserDatabase", "Users", this.username, "About Me", this.aboutMe);
    }

    public void setFoodSelection(String foodSelection){
        this.foodSelections = foodSelection;
        mongoDB.updateDatabase("UserDatabase", "Users", this.username, "Food Selection", this.foodSelections);
    }

    public void setFacultySelection(String facultySelection){
        this.facultySelections = facultySelection;
        mongoDB.updateDatabase("UserDatabase", "Users", this.username, "Faculty Selection", this.facultySelections);
    }

    public void setFacilitiesSelection(String facilitiesSelection){
        this.facilitiesSlection = facilitiesSelection;
        mongoDB.updateDatabase("UserDatabase", "Users", this.username, "Facilities Selection", this.facilitiesSlection);
    }

    public void setDormSelection(String dormSelection){
        this.dormSelection = dormSelection;
        mongoDB.updateDatabase("UserDatabase", "Users", this.username, "Dorm Selection", this.dormSelection);
    }

    //TO-DO
    public void setClasses(Class selectedClass){
        this.classes.addClasses(selectedClass);
        mongoDB.updateDatabase("UserDatabase", "Users", this.username, "Class selection", "TO-DO");
    }

    public void updatePermissionLevel(String permissionLevel){
        this.permissionLevel = permissionLevel;
        mongoDB.updateDatabase("UserDatabase", "Users", this.username, "Permission Level", this.permissionLevel);
    }

    public String getDisplayName(){
        return this.displayName;
    }

    public String getUsername(){
        return this.username;
    }

    public String getAboutMe(){
        return this.aboutMe;
    }

    public String getPFP(){
        return this.pfpString;
    }

    public String getInterests(){
        return this.interests;
    }

    public String getContactInformation(){
        return this.contactInformation;
    }

    public String getPermissionLevel(){
        return this.permissionLevel;
    }

    public String getFoodSelection(){
        return this.foodSelections;
    }

    public String getDormSelection(){
        return this.dormSelection;
    }

    public String getFacultySelection(){
        return this.facultySelections;
    }

    public String getFacilitiesSelection(){
        return this.facilitiesSlection;
    }

    public List<Object> getPhotoGallery(){
        return photoGallery;
    }



    /** 
    //Remove this later, prob isnt needed
    public String getPFPString(){
        return this.pfpString;
    }*/


    public BufferedImage createImg(String fileName){

        try{
            //Img could be read, so it returns the BufferedImage of it.
            return ImageIO.read(new File(fileName) );
        }
        catch(IOException e){

        }

        //Img couldn't be read, so a null is returned
        return null;
    }


    public void displayPFP(){

        Graphics2D g = (Graphics2D) pfp.getGraphics();
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLUE);
        g.drawRect(10, 10, pfp.getWidth() - 20, pfp.getHeight() - 20);

        JLabel picLabel = new JLabel(new ImageIcon(pfp));

        JPanel jPanel = new JPanel();
        jPanel.add(picLabel);

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(new Dimension(pfp.getWidth(), pfp.getHeight()));
        f.add(jPanel);
        f.setVisible(true);
    }


    /**
     * This takes in an image, and then it goes through encoding the image to 64string, and when it 
     * converts the image to 64string, then it saves it to "PFP" for database.
     * @param image
     */
    public String imageToBase64String(BufferedImage image){

        String base64String = null;

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try{
            ImageIO.write(image, "png", os);
            base64String = Base64.getEncoder().encodeToString(os.toByteArray());
        }
        catch (IOException e){
            throw new UncheckedIOException(e);
        }


        mongoDB.updateDatabase("UserDatabase", "Users", this.username, "PFP", base64String);

        return base64String;

    }

    /**
     * Converts the base64string parameter and returns it as BufferedImage.
     * @param base64String
     * @return BufferedImage now that it's been converted
     */
    public BufferedImage base64StringToImg(String base64String) {
        
        try {
            return ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(base64String)));
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    /**
     * This saves the information and will only be used when the user is being initialized
     */
    public void saveInformation(){

        MongoCollection<Document> userCollection = mongoDB.returnCollection("UserDatabase", "Users");

        //Creating new document to insert into the Users collection, so leaderboard can grab the information later
        Document document = new Document("Username", username).append("Password", password).append("Display Name", displayName)
        .append("Contact Information", this.contactInformation).append("Interests", interests).append("Food Selection", foodSelections)
        .append("Faculty Selection", facultySelections).append("Facilities Selection", facilitiesSlection).append("Dorm Selection", dormSelection)
        .append("About Me", aboutMe).append("PFP", pfpString).append("Permission Level", this.permissionLevel).append("Photo Gallery", photoGallery);

        userCollection.insertOne(document);

    }

    /**
     * This access's the information to the user that was saved in the db, and then sets the values from the database,
     * to the variables that were made in this class.
     */
    public void accessUserInformation(String inputUsername){
        
        MongoCollection<Document> userCollection = mongoDB.returnCollection("UserDatabase", "Users");

        for(Document doc : userCollection.find() ){
            if( inputUsername.compareTo( doc.getString("Username") ) == 0){
                this.displayName = doc.getString("Display Name");
                this.username = doc.getString("Username");
                this.password = doc.getString("Password");
                this.contactInformation = doc.getString("Contact Information");
                this.interests = doc.getString("Interests");
                this.foodSelections = doc.getString("Food Selection");
                this.facultySelections = doc.getString("Faculty Selection");
                this.facilitiesSlection = doc.getString("Facilities Selection");
                this.dormSelection = doc.getString("Dorm Selection");
                this.aboutMe = doc.getString("About Me");
                this.permissionLevel = doc.getString("Permission Level");
                this.pfpString = doc.getString("PFP");

                //this.photoGallery = (List<Object>) doc.getList(doc, photoGallery.getClass() );

                
            }
            
        }
    }


}


