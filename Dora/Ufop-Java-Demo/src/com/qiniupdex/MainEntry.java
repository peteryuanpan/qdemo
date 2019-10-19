package com.qiniupdex;

import com.qiniupdex.ufop.UfopServer;

public class MainEntry {

    public static void main(String[] args) {
        try {
            UfopServer server = new UfopServer();
            System.out.println("Start to listen server ...");
            server.listen();
        } catch (Exception ex) {
            System.out.println("Start server error:" + ex.getMessage());
        }
    }
}
