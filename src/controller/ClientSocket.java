/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import datatransferobject.MessageEnum;
import datatransferobject.Package;
import datatransferobject.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2dam
 */
public class ClientSocket {
    
    static final int PUERTO = 5000;
    static final String HOST = "localhost";
    Package p = new Package();
    User u = new User();
    MessageEnum me = null;
    
    public Package conexionConServidor(Package p) {
        try {
            Socket clientSocket;
            clientSocket = new Socket(HOST, PUERTO);
            enviarPackage(clientSocket, p);
            p = recibirPackage(clientSocket);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }
    
    private void enviarPackage(Socket clientSocket, Package p) {
        
        OutputStream os = null;
        try {
            os = clientSocket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(p);
        } catch (IOException ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Package recibirPackage(Socket clientSocket) {
        
        ObjectInputStream ois = null;
        try {
            InputStream is = clientSocket.getInputStream();
            ois = new ObjectInputStream(is);
            p = (Package) ois.readObject();
            p.getDatos(u, me);
        } catch (IOException ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p;
    }
    
}
