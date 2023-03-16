package com.example;







public class MainMenu {
    
    private User user;
    private HAM ham;
    private Faculty faculty;
    private Facilities facilities;
    private Forums forum;
    private LeaderBoard lb;
    
    public MainMenu(User user){

    }

    public MainMenu(){

    }


    public User getUser(){
        return user;
    }

    public void createUser(String displayName, String username, String password){
        this.user = new User(displayName, username, password);
    }


}
