package com.example;

/**
 * 
 * @Author: Andrew Skevington-Olivera
 * @Date: 9-3-2023
 */

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.IIOException;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

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



    this.server.createContext("/Create New User", new CreateUser( this.users ) );
    this.server.createContext("/Create Old User", new Login( this.users) );
    this.server.createContext("/Logout", new Logout( this.users) );
    this.server.createContext("/Liked Foods", new AddHAMFood( this.users) );
    this.server.createContext("/Selected Faculty", new AddFaculty( this.users) );
    this.server.createContext("/Liked Facilities", new AddFacilities( this.users) );
    this.server.createContext("/Selected Dorm", new AddDorm( this.users) );
    this.server.createContext("/Update Display Name", new UpdateDisplayName( this.users));
    this.server.createContext("Update Password", new UpdatePassword( this.users ) );
    this.server.createContext("/Upload PFP", new UploadPFP( this.users ) );
    this.server.createContext("/Update About me", new UpdateAboutMe( this.users ) );
    this.server.createContext("/Update Contact Information", new UpdateContactInformation( this.users ) );


    
    //Set new photos to photo gallery


    //Return username
    //Return display name
    //Return password (?) Prob should just be kept all backend tbh
    //Return pfp
    //Return photo gallery
    //Return Food / Dorm / Facilities / Faculty Selection
    //Return interests
    //Return about me

    //Have an update LB function


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
      String response = "Hello World!";
      t.sendResponseHeaders(200, response.length());
      t.getResponseBody().write(response.getBytes());
      t.getResponseBody().close();
  }
}


class CreateUser implements HttpHandler{

  private UserList newUsersList = UserList.getInstance();

  public CreateUser(UserList creatingUser){
    this.newUsersList = creatingUser;
  }
  
  public void handle(HttpExchange exchange) throws IOException {
    
    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String displayName = params.get("Display Name");
    String username = params.get("Username");
    String password = params.get("Password");

    this.newUsersList.createUser(displayName, username, password);

    String response = "Hello World!";
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

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    //String displayName = params.get("Display Name");
    String username = params.get("Username");
    String password = params.get("Password");

    this.userList.checkLogin(username, password);
  }

}



class Logout implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public Logout(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException {

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());
    
    String username = params.get("Username");
    User toRemoveUser = userList.accessUser(username);
    userList.removeUser(toRemoveUser);

  } 
}

class AddHAMFood implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public AddHAMFood(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String likedFoods = params.get("Liked Foods");
    User user = userList.accessUser(username);

    HAM ham = new HAM(user);
    ham.addFood(likedFoods);
    ham.completeTask();
  }
}

class AddFaculty implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public AddFaculty(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String selectedFaculty = params.get("Selected Faculty");
    User user = userList.accessUser(username);

    Faculty faculty = new Faculty(user);
    faculty.addFaculty(selectedFaculty);
    faculty.completeTask();
  }
}

class AddFacilities implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public AddFacilities(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String likedFacilities = params.get("Liked Facilities");
    User user = userList.accessUser(username);

    Facilities facilities = new Facilities(user);
    facilities.addFacilities(likedFacilities);
    facilities.completeTask();
  }
}

class AddDorm implements HttpHandler{

  private UserList userList = UserList.getInstance();

  public AddDorm(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String selectedDorm = params.get("Selected Dorm");
    User user = userList.accessUser(username);

    Dorm dorm = new Dorm(user);
    dorm.addDorm(selectedDorm);
    dorm.completeTask();
  }
}

class UpdateDisplayName implements HttpHandler{
  
  private UserList userList = UserList.getInstance();

  public UpdateDisplayName(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String newDisplayName = params.get("New Display Name");
    User user = userList.accessUser(username);
    user.updateDisplayName(newDisplayName);
  }
}

class UpdatePassword implements HttpHandler{
  
  private UserList userList = UserList.getInstance();

  public UpdatePassword(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String newPassword = params.get("New Password");
    User user = userList.accessUser(username);
    user.updatePassword(newPassword);
  }
}

class UploadPFP implements HttpHandler{
  
  private UserList userList = UserList.getInstance();

  public UploadPFP(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String newPFPFile = params.get("New PFP File");
    User user = userList.accessUser(username);
    user.uploadPFP(newPFPFile);
  }
}

class UpdateAboutMe implements HttpHandler{
  
  private UserList userList = UserList.getInstance();

  public UpdateAboutMe(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String newAboutMe = params.get("New About me");
    User user = userList.accessUser(username);
    user.updateAboutMe(newAboutMe);
  }
}

class UpdateContactInformation implements HttpHandler{
  
  private UserList userList = UserList.getInstance();

  public UpdateContactInformation(UserList users){
    this.userList = users;
  }

  public void handle(HttpExchange exchange) throws IOException{

    Map<String, String> params = Webserver.queryToMap(exchange.getRequestURI().getQuery());

    String username = params.get("Username");
    String newContactInfo = params.get("New Contact Information");
    User user = userList.accessUser(username);
    user.updateContactInfo(newContactInfo);
  }
}