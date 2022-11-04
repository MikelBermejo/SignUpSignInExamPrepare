/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import datatransferobject.MessageEnum;
import datatransferobject.Model;
import datatransferobject.Package;
import datatransferobject.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julen
 */
public class ModelImplementation implements Model {
    private static final int PORT = 5000;
    private static final String HOST = "localhost";
    private Socket sckt;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private static final Logger LOGGER = Logger.getLogger("ModelImplementation");
    /**
     * Method that takes a user from the view and sends a package to the server
     * @param user Class that has all data from a user
     * @return p Class that contains a user and a MessageEnum
     */

    @Override
    public User doSignIn(User user) {
        try {
            sckt = new Socket(HOST,PORT);
            oos = new ObjectOutputStream(sckt.getOutputStream());
            ois = new ObjectInputStream(sckt.getInputStream());
            Package pack = new Package(user,MessageEnum.RE_SIGNIN);
            oos.writeObject(pack);
            
            pack = (Package) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ModelImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
                ois.close();
                sckt.close();
            } catch (IOException ex) {
                Logger.getLogger(ModelImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }
    /**
     * Method that takes a user from the view and sends a package to the server
     * @param user Class that has all data from a user
     * @return p Class that contains a user and a MessageEnum
     */

    @Override
    public void doSignUp(User user) {
        try {
            sckt = new Socket(HOST,PORT);
            oos = new ObjectOutputStream(sckt.getOutputStream());
            
            Package pack = new Package(user,MessageEnum.RE_SIGNUP);
            oos.writeObject(pack);
            
            ois = new ObjectInputStream(sckt.getInputStream());
            pack = (Package) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ModelImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                oos.close();
                ois.close();
                sckt.close();
            } catch (IOException ex) {
                Logger.getLogger(ModelImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
