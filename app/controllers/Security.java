package controllers;

import models.*;
 
public class Security extends Secure.Security {
	
	public static User connectedUser () {
		User user = (User) renderArgs.get("user");
		if (isConnected() && user == null) {
			user = User.find("byName", connected()).first();
			renderArgs.put("user", user);
		}
		
		return user;
	}
	
    static boolean authenticate(String username, String password) {
    	User c = User.find("byName", username).first();
    	return c != null && c.password.equals(password);
    }
    
    static boolean check(String profile) {
        User user = User.find("byName", connected()).first();
        return user.role != null && user.role.equalsIgnoreCase(profile);
      }
    
    static void onAuthenticated() {
    	Application.index();
    }
    
    static void onDisconnected() {
            Application.index();
    }
}