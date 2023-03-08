
package com.example;



/**
 * Random thoughts to not forget
 * Create a folder called Database 
 * Save a folder called UserDatabase into it w/ multiple user.txt files to go through (or could just have 1 giant .txt file and a line for each user information)
 * Have HAM.txt for food options
 * Save a folder called ClassDatabase and have a class.txt for each class (or could just have 1 giant .txt file and a line for each class information)
 * Have Faculty.txt containing all Faculty names and maybe some information on what they do
 * Have facilities.txt for each of the different places and description of each place
 * Have links.txt to store Catalyst articles
 */


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.script.ScriptException;

public class Testing {
    

    public static void main(String[] args) throws ScriptException, IOException{


        //Name, professors, startTime, endTime, startDate, endDate, Days
        ArrayList<Days> dayArray = new ArrayList<Days>(); //Figure out y tf I need to have a Days[] array out here initialized instead of in Class() as {day1, day2}
        dayArray.add(Days.Tuesday);
        dayArray.add(Days.Thursday);
        //Kinda would be annoying to have to have a day[] array made everytime I wanna enter any Days to initialize a new Class()
        Class softwareEngineering = new Class("Software Engineering", "Tania Roy", "9:00 AM", "10:20 AM", "4/2/2023", "20/5/2023", dayArray );
        System.out.println(softwareEngineering);
        System.out.println(softwareEngineering.getName() );
        //softwareEngineering.saveClass();


        User newUser = new User("Jacob", "jacobIsCool", "jacob123!", "jacob123@gmail.com", "Video Games, Sports", "I like to play sports and games w/ friends");
        //newUser.saveInformation();
        UserList currentUsers = new UserList();
        currentUsers.addUser(newUser);


        //Forums forum = new Forums();
        //forum.updateUserList(currentUsers.getUsersArray() );
        //forum.addMessage("Test1 !");
        //forum.addMessage("Testing time !!!");

        //Catalyst testCatalyst = new Catalyst();
        //testCatalyst.readLink("aaah doesn't matter tbh");

        //saveToMongo testingMongo = new saveToMongo();
        //testingMongo.testMongo();

        //Dorm testingDorm = new Dorm(newUser);
        //testingDorm.completeTask();

        //HAM testHAM = new HAM();
        //testHAM.completeTask();

        //Facilities testFacilities = new Facilities();
        //testFacilities.completeTask();

        //Faculty testFaculty = new Faculty();
        //testFaculty.completeTask();

        //Classes testClasses = new Classes();
        //testClasses.completeTask();


        LeaderBoard lbTest = new LeaderBoard();
        lbTest.getTasksInformation();



    }
}
