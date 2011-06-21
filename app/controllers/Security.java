package controllers;

import models.*;
 
public class Security extends Secure.Security {
	
	public static User connectedUser () {
		if (isConnected())
			return User.find("byName", connected()).first();
		
		return null;
	}
	
    static boolean authenticate(String username, String password) {
    	User c = User.find("byName", username).first();
    	return c != null && c.password.equals(password);
    }
    
    static void onAuthenticated() {
    	Accounts.index();
    }
    
    static void onDisconnected() {
    	try {
            Application.index();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}