/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ClientSocket;
import datatransferobject.MessageEnum;
import datatransferobject.Model;
import datatransferobject.Package;
import datatransferobject.User;

/**
 *
 * @author Julen
 */
public class ModelImplementation implements Model {
    
    /**
     * Method that takes a user from the view and sends a package to the server
     * @param user Class that has all data from a user
     * @return p Class that contains a user and a MessageEnum
     */

    @Override
    public Package doSignIn(User user) {
        Package p = new Package(user, MessageEnum.RE_SIGNIN);
        ClientSocket cs = new ClientSocket();
        p = cs.conexionConServidor(p);
        return p;

    }
    /**
     * Method that takes a user from the view and sends a package to the server
     * @param user Class that has all data from a user
     * @return p Class that contains a user and a MessageEnum
     */

    @Override
    public Package doSignUp(User user) {
        Package p = new Package(user, MessageEnum.RE_SIGNUP);
        ClientSocket cs = new ClientSocket();
        p = cs.conexionConServidor(p);
        return p;

    }

}
