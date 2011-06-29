package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends BaseController {

    public static void index(){
        
        if (renderArgs.get("user") == null)
			try {
				Secure.login();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else {
            List<Category> categories = Category.findAll();
            render(categories);
        }
    }

}