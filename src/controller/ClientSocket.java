/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author 2dam
 */
public class ClientSocket {
    
    static final int PUERTO = 5000;
    static final String HOST = "localhost";
    
    public void conexionConServidor() {
        try {
            Socket socket;
            socket = new Socket(HOST, PUERTO);
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }
    
    public static void main(String[] args) {
        ClientSocket clientSocket = new ClientSocket();
        clientSocket.conexionConServidor();
    }
    
}
