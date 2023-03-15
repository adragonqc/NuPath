package com.example;

/**
 * 
 * @Author: Andrew Skevington-Olivera
 * @Date: 9-3-2023
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.IIOException;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.awt.image.BufferedImage;

//Resource used for setting up the api: https://www.codeproject.com/Tips/1040097/Create-a-Simple-Web-Server-in-Java-HTTP-Server
//2nd website: http://www.java2s.com/Code/Java/Network-Protocol/MinimalHTTPServerbyusingcomsunnethttpserverHttpServer.htm


public class Webserver{


  //Port is for the server address, and server is setup so HttpRequests can be made and replies can be made
  private UserList users = new UserList();
  private int port;
  private HttpServer server;

  //Need to setup a main menu to grab all of the different things
  public Webserver(int port) throws IOException{
    this.port = port;
    this.server = HttpServer.create( new InetSocketAddress(this.port), 0);


    this.server.createContext("/Ping", new IndexHandler() );
    this.server.createContext("/CreateNewUser", new CreateUser( this.users ) );
    this.server.createContext("/CreateOldUser", new Login( this.users) );
    this.server.createContext("/Logout", new Logout( this.users) );
    this.server.createContext("/LikedFoods", new AddHAMFood( this.users) );
    this.server.createContext("/SelectedFaculty", new AddFaculty( this.users) );
    this.server.createContext("/LikedFacilities", new AddFacilities( this.users) );
    this.server.createContext("/SelectedDorm", new AddDorm( this.users) );
    this.server.createContext("/UpdateDisplayName", new UpdateDisplayName( this.users));
    //this.server.createContext("UpdatePassword", new UpdatePassword( this.users ) ); //This doesn't work and I'm not sure why but updating password
    //isn't crucial for the user as of right now, so this is to be fixed later.
    this.server.createContext("/UploadPFP", new UploadPFP( this.users ) );
    //this.server.createContext("UploadImgToPhotos", new UploadImgToPhotoGallery( this.users ) );
    this.server.createContext("/UpdateAboutme", new UpdateAboutMe( this.users ) );
    this.server.createContext("/UpdateContactInformation", new UpdateContactInformation( this.users ) );
    this.server.createContext("/UpdateInterests", new UpdateInterests( this.users ) );
    this.server.createContext("/UpdateCatalystNotes", new UpdateCatalyst( this.users) );
    this.server.createContext("/AddToPhotoGallery", new AddImgToPhotoGallery( this.users) );
    this.server.createContext("/ReturnUsername", new ReturnUsername( this.users ) );
    this.server.createContext("/ReturnDisplayName", new ReturnDisplayName( this.users ) );
    this.server.createContext("/ReturnPFP", new ReturnPFP( this.users ) );
    this.server.createContext("/ReturnInterests", new ReturnInterests( this.users ) );
    this.server.createContext("/ReturnAboutMe", new ReturnAboutMe( this.users ) );
    this.server.createContext("/ReturnFood", new ReturnFood( this.users ) );
    this.server.createContext("/ReturnDorm", new ReturnDorm( this.users ) );
    this.server.createContext("/ReturnFacilities", new ReturnFacilities( this.users ) );
    this.server.createContext("/ReturnFaculty", new ReturnFaculty( this.users ) );
    this.server.createContext("/ReturnContactInfo", new ReturnContactInformation( this.users) ); 
    this.server.createContext("/ReturnCatalystNotes", new ReturnCatalystNotes( this.users) );
    this.server.createContext("/UpdateLeaderboard", new UpdateLeaderBoard() );
    this.server.createContext("/ReturnLBInfo", new ReturnLBInformation() ); //This needs to be fixed and 2d ArrayList should be sent as a response

    
    //Set new photos to photo gallery

    //Work on returning BufferedImages because that currently isn't working for these 2
    //Return pfp
    //Return photo gallery

    


    this.server.start();

  }


  public HttpServer getServer(){
    return this.server;
  }


  public static Map<String, String> queryToMap(String query) {
    if(query == null) {
        return null;
    }
    Map<String, String> result = new HashMap<>();
    for (String param : query.split("&")) {
        String[] entry = param.split("=");
        if (entry.length > 1) {
            result.put(entry[0], entry[1]);
        }else{
            result.put(entry[0], "");
        }
    }

    return result;
  }


}

class IndexHandler implements HttpHandler {
  public void handle(HttpExchange t) throws IOException {
      String response = "Response!";
      t.sendResponseHeaders(200, response.length());
      t.getResponseBody().write(response.getBytes());
      t.getResponseBody().close();
  }
}


class GetAllUsernames implements HttpHandler{

  private UserList newUsersList = UserList.getInstance();

  public GetAllUsernames(UserList users){
    this.newUsersList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    
    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String listUsernames = newUsersList.returnAllUserNames();

    //The username is the token being sent back to the frontend to grab the information from the 
    //users on the website for more information requests.
    //Should probably be changed to something more secure, but that's for later.
    String response = String.valueOf(listUsernames);
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class CheckUsers implements HttpHandler{
  
  private UserList newUsersList = UserList.getInstance();

  public CheckUsers(UserList users){
    this.newUsersList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    
    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String checkIfUser = newUsersList.checkUsername(username);

    //The username is the token being sent back to the frontend to grab the information from the 
    //users on the website for more information requests.
    //Should probably be changed to something more secure, but that's for later.
    String response = String.valueOf(checkIfUser);
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}


class CreateUser implements HttpHandler{

  private UserList newUsersList = UserList.getInstance();

  public CreateUser(UserList creatingUser){
    this.newUsersList = creatingUser;
  }
  
  public void handle(HttpExchange exchange) throws IOException {

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    
    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String displayName = params.get("DisplayName");
    String username = params.get("Username");
    String password = params.get("Password");
    String contactInfo = params.get("ContactInformation");

    boolean alreadyUsername = newUsersList.createUser(displayName, username, password, contactInfo);

    //The username is the token being sent back to the frontend to grab the information from the 
    //users on the website for more information requests.
    //Should probably be changed to something more secure, but that's for later.
    String response = String.valueOf(alreadyUsername);
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
  
}


class Login implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public Login(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException {

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String password = params.get("Password");

    String response = userList.checkLogin(username, password);

    //The username is the token being sent back to the frontend to grab the information from the 
    //users on the website for more information requests.
    //Should probably be changed to something more secure, but that's for later.
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }

}



class Logout implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public Logout(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException {

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());
    
    String username = params.get("Username");
    User toRemoveUser = userList.accessUser(username);
    userList.removeUser(toRemoveUser);

    String response = "The user has been logged out";
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  } 
}

class AddHAMFood implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public AddHAMFood(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String likedFoods = params.get("LikedFoods");
    User user = userList.accessUser(username);

    HAM ham = new HAM(user);
    ham.addFood(likedFoods);
    ham.completeTask();
    user.setFoodSelection(likedFoods);

    String response = "Response";
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class AddFaculty implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public AddFaculty(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String selectedFaculty = params.get("SelectedFaculty");
    User user = userList.accessUser(username);

    Faculty faculty = new Faculty(user);
    faculty.addFaculty(selectedFaculty);
    faculty.completeTask();
    user.setFacultySelection(selectedFaculty);

    String response = "Response";
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class AddFacilities implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public AddFacilities(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String likedFacilities = params.get("LikedFacilities");
    User user = userList.accessUser(username);

    Facilities facilities = new Facilities(user);
    facilities.addFacilities(likedFacilities);
    facilities.completeTask();
    user.setFacilitiesSelection(likedFacilities);

    String response = "Response";
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class AddDorm implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public AddDorm(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String selectedDorm = params.get("SelectedDorm");
    User user = userList.accessUser(username);

    Dorm dorm = new Dorm(user);
    dorm.addDorm(selectedDorm);
    dorm.completeTask();
    user.setDormSelection(selectedDorm);

    String response = "Response";
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class UpdateDisplayName implements HttpHandler{
  
  private UserList userList = UserList.getInstance();

  public UpdateDisplayName(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String newDisplayName = params.get("NewDisplayName");
    User user = userList.accessUser(username);
    user.updateDisplayName(newDisplayName);

    String response = "User's display name has been updated";
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class UpdatePassword implements HttpHandler{
  
  private UserList userList = UserList.getInstance();

  public UpdatePassword(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String newPassword = params.get("NewPassword");
    User user = userList.accessUser(username);
    user.updatePassword(newPassword);

    String response = "User's password has been updated";
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class UploadPFP implements HttpHandler{
  
  private UserList userList = UserList.getInstance();

  public UploadPFP(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String newPFPFile = params.get("NewPFPFile");
    User user = userList.accessUser(username);
    user.uploadPFP(newPFPFile);

    String response = "User's pfp has been uploaded";
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class UploadImgToPhotoGallery implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public UploadImgToPhotoGallery(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String newImg = params.get("NewImgfile");
    User user = userList.accessUser(username);
    System.out.println();
    System.out.println("Breaking before its adding it to photo gallery");
    System.out.println();
    user.addImgToPhotos(newImg);
    System.out.println();
    System.out.println("Breaking after adding it to photo gallery");
    System.out.println();

    String response = "Image was added to Photo Gallery";
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();

  }
}

class UpdateAboutMe implements HttpHandler{
  
  private UserList userList = UserList.getInstance();

  public UpdateAboutMe(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String newAboutMe = params.get("NewAboutme");
    User user = userList.accessUser(username);
    user.updateAboutMe(newAboutMe);

    String response = "User's About me has been updated";
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class UpdateContactInformation implements HttpHandler{
  
  private UserList userList = UserList.getInstance();

  public UpdateContactInformation(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String newContactInfo = params.get("NewContactInformation");
    User user = userList.accessUser(username);
    user.updateContactInfo(newContactInfo);

    String response = "Response";
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class UpdateCatalyst implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public UpdateCatalyst(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String token = params.get("Username");
    String notes = params.get("Notes");
    User user = userList.accessUser(token);
    user.updateCatalystNote(notes);

    String response = notes;
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();

  }
}

class UpdateInterests implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public UpdateInterests(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String token = params.get("Username");
    String interest = params.get("Interest");
    System.out.println(token);
    User user = userList.accessUser(token);
    System.out.println( user.getUsername() );
    user.updateInterests(interest);

    System.out.println();
    System.out.println("This is the username " + user.getUsername() );
    System.out.println();

    String response = interest;
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class ReturnUsername implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public ReturnUsername(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String token = params.get("Username");

    User user = userList.accessUser(token);
    String username = user.getUsername();

    String response = username;
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class ReturnDisplayName implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public ReturnDisplayName(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String token = params.get("Username");

    User user = userList.accessUser(token);
    String displayName = user.getDisplayName();

    String response = displayName;
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class ReturnPFP implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public ReturnPFP(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String token = params.get("Username");

    User user = userList.accessUser(token);
    String pfp = user.getPFP();

    String response = pfp;
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes() );
    exchange.getResponseBody().close();
  }
}

class ReturnPhotoGallery implements HttpHandler{
  private UserList userList = UserList.getInstance();

  public ReturnPhotoGallery(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{
    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String token = params.get("Username");

    User user = userList.accessUser(token);
    ArrayList<String> photoGallery = user.getPhotoGallery();

    for(Object obj : photoGallery){
      String response = String.valueOf(obj);
      exchange.sendResponseHeaders(200, response.length());
      exchange.getResponseBody().write(response.getBytes() );
      exchange.getResponseBody().close();
    }
  }
}

class ReturnCatalystNotes implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public ReturnCatalystNotes(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{
    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String token = params.get("Username");
    User user = userList.accessUser(token);
    String notes = user.getCatalystNotes();

    String response = notes;
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class ReturnAboutMe implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public ReturnAboutMe(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String token = params.get("Username");

    User user = userList.accessUser(token);
    String aboutMe = user.getAboutMe();

    String response = aboutMe;
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class ReturnInterests implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public ReturnInterests(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String token = params.get("Username");

    User user = userList.accessUser(token);
    String interests = user.getInterests();

    String response = interests;
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class ReturnFood implements HttpHandler{

  private UserList userList = new UserList();

  public ReturnFood(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{ 

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String token = params.get("Username");
    User user = userList.accessUser(token);
    String food = user.getFoodSelection();

    String response = food;
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();

  }
}

class ReturnDorm implements HttpHandler{

  private UserList userList = new UserList();

  public ReturnDorm(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    
    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String token = params.get("Username");
    User user = userList.accessUser(token);
    String dorm = user.getDormSelection();

    String response = dorm;
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class ReturnFacilities implements HttpHandler{

  private UserList userList = new UserList();

  public ReturnFacilities(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    
    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());
    
    String token = params.get("Username");
    User user = userList.accessUser(token);
    String facilities = user.getFacilitiesSelection();

    String response = facilities;
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class ReturnFaculty implements HttpHandler{

  private UserList userList = new UserList();
  
  public ReturnFaculty(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String token = params.get("Username");
    User user = userList.accessUser(token);
    String faculty = user.getFacultySelection();

    String response = faculty;
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}


class ReturnContactInformation implements HttpHandler{

  private UserList userList = new UserList();
  
  public ReturnContactInformation(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String token = params.get("Username");
    User user = userList.accessUser(token);
    String contactInfo = user.getContactInformation();

    String response = contactInfo;
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class AddImgToPhotoGallery implements HttpHandler{

  private UserList userList = new UserList();

  public AddImgToPhotoGallery(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String token = params.get("Username");
    String newImg = params.get("New Image");
    User user = userList.accessUser(token);
    user.addImgToPhotos(newImg);

    String response = token;
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}



class UpdateLeaderBoard implements HttpHandler{

  public UpdateLeaderBoard(){

  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    
    LeaderBoard lb = new LeaderBoard();
    lb.getTasksInformation();

    String response = "Response";
    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
  }
}

class ReturnLBInformation implements HttpHandler{

  public ReturnLBInformation(){

  }

  public void handle(HttpExchange exchange) throws IOException{

    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

    LeaderBoard lb = new LeaderBoard();
    ArrayList<ArrayList<String>> lbInfo = lb.returnLists();

    ArrayList<String> responseList = new ArrayList<>();

    for( ArrayList<String> arrString : lbInfo){
      String inputResponse = "";
      for(int i = 0; i < 2; i++){
        inputResponse += arrString.get(i);
        if(i == 0){
          inputResponse += " ";
        }
      }
      responseList.add(inputResponse);
    }

    String response = ""; 

    for(String str : responseList){
      response += str + " ";
    }

    exchange.sendResponseHeaders(200, response.length());
    exchange.getResponseBody().write(response.getBytes());
    exchange.getResponseBody().close();
    
  }
}