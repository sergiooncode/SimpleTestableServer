package com.sperez.jhs;

class SimpleServerThread implements Runnable {
    private String[] args = {};
    private SimpleServer server;
    private Thread t;

    public SimpleServerThread(){
        this.server = new SimpleServer();
        this.t = new Thread(this);
    }

    public void run() {
        try {
            server.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(){
        t.start();
    }

    public void terminate(){
        t.interrupt();
        return;
    }
}