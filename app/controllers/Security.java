package controllers;

import models.User;

public class Security extends Secure.Security{

	static boolean authenticate(String username, String password) {
		User user = User.find("username",username).first();
		if(user!=null && user.password.equals(password) && user.flag == 1){
			return true;
		}
        return false;
    }
	
	static void onAuthenticated(){
		Application.index();
	}
}
