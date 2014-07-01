package com.sperez.jhs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SimpleServerTest {
    private SimpleServerThread server;
    private SimpleClient client;
    private String response;
    private String host = "localhost";
    private int port = 5000;

    @Before
    public void setUp() throws Exception {
        server = new SimpleServerThread();
        client = new SimpleClient(host, port);
    }

    @After
    public void tearDown() throws Exception {
        server.terminate();
        client.close();
    }

    @Test
    public void testSendingSimpleGetRequest(){
        server.start();
        client.connect();
        client.send("GET /index.html HTTP/1.1\r\n");
        response = client.read();

        assertEquals("HTTP/1.1 200", response);
    }
}