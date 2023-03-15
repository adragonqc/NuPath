
package com.example;



import java.io.DataInputStream;
import java.io.DataOutputStream;

//Resource for img to base64string and base64string to img: https://stackoverflow.com/questions/7178937/java-bufferedimage-to-png-format-base64-string#:~:text=Here%20is%20the%20code%20that,os)%3B%20String%20result%20%3D%20Base64.

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
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.script.ScriptException;

import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Testing {
    
  
    public static void main(String[] args) throws ScriptException, IOException{


        //Name, professors, startTime, endTime, startDate, endDate, Days
        ArrayList<Days> dayArray = new ArrayList<Days>(); //Figure out y tf I need to have a Days[] array out here initialized instead of in Class() as {day1, day2}
        dayArray.add(Days.Tuesday);
        dayArray.add(Days.Thursday);
        //Kinda would be annoying to have to have a day[] array made everytime I wanna enter any Days to initialize a new Class()
        //Class softwareEngineering = new Class("Software Engineering", "Tania Roy", "9:00 AM", "10:20 AM", "4/2/2023", "20/5/2023", dayArray );
        //System.out.println(softwareEngineering);
        //System.out.println(softwareEngineering.getName() );
        //softwareEngineering.saveClass();

        //Converting string to enum ? Maybe a bunch of switch cases like case:"Monday" return Days.Monday


        Webserver server = new Webserver(9000);

    }

}

