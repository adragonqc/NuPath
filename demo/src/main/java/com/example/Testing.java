
package com.example;



import java.io.DataInputStream;
import java.io.DataOutputStream;

//Resource for img to base64string and base64string to img: https://stackoverflow.com/questions/7178937/java-bufferedimage-to-png-format-base64-string#:~:text=Here%20is%20the%20code%20that,os)%3B%20String%20result%20%3D%20Base64.
//Resource for MongoDB: https://www.mongodb.com/docs/manual/introduction/
//Resource for Webserver: http://www.java2s.com/Code/Java/Network-Protocol/MinimalHTTPServerbyusingcomsunnethttpserverHttpServer.htm




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

        Webserver server = new Webserver(80);

    }

}

