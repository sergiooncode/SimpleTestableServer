package com.sperez.jhs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.IOException;
import java.io.DataOutputStream;

class SimpleClient {
    private Socket clientSocket = null;
    private DataOutputStream outToServer;
    private String responseMessageLine;
    private BufferedReader inFromClient;
    private String host;
    private int port;

    public SimpleClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    void connect(){
        try {
            clientSocket = new Socket(host, port);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    void send(String request) {
        try {
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String read(){
        try {
            inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            responseMessageLine = inFromClient.readLine();
            System.out.println ("Response: " + responseMessageLine);
        } catch(IOException e){
            e.printStackTrace();
        }
        return responseMessageLine;
    }

    void close(){
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}