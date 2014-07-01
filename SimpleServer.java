package com.sperez.jhs;

import java.io.*;
import java.net.*;

class SimpleServer {
    static private int port = 5000;
    static private Socket connectionSocket;
    static private BufferedReader inFromClient;
    static private OutputStreamWriter outToClient;
    static private String requestMessageLine;

    static void connect(){
        try{
            ServerSocket listenSocket = new ServerSocket (port);
            System.out.println ("Web server waiting for request on port " + port);
            connectionSocket = listenSocket.accept();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    static void readRequest(){
        try{
            inFromClient = new BufferedReader (new InputStreamReader(connectionSocket.getInputStream()));
            requestMessageLine = inFromClient.readLine();
            System.out.println ("Request: " + requestMessageLine);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    static void sendResponse(){
        try{
            BufferedOutputStream out = new BufferedOutputStream (connectionSocket.getOutputStream());
            outToClient = new OutputStreamWriter(out, "US-ASCII");
            outToClient.write("HTTP/1.1 200\r\n");
            outToClient.flush();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    static void disconnect(){
        try {
            connectionSocket.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    public static void main (String args[]){
        connect();
        readRequest();
        sendResponse();
        disconnect();
    }
}