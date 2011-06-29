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
    
    static boolean check(String profile) {
        User user = User.find("byUserID", connected()).first();
        return user.role != null && user.role.equalsIgnoreCase(profile);
      }
    
    static void onAuthenticated() {
    	Application.index();
    }
    
    static void onDisconnected() {
            Application.index();
    }
}