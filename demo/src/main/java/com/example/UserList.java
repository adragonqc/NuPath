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

public class UserList {
    
    private ArrayList<User> userArray = new ArrayList<>();

    public UserList(){

    }


    public void addUser(User user){
        userArray.add(user);
    }


    public void removeUser(){

    }

    public ArrayList<User> getUsersArray(){
        return userArray;
    }

    public User getUser0(){
        return userArray.get(0);
    }
    
}
