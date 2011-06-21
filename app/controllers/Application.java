package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    @Before
    public static void secure() {
        renderArgs.put("user", Security.connectedUser());
    }

    public static void index() throws Throwable{
        
        if (renderArgs.get("user") == null)
            Secure.login();
        else {
            List<Category> categories = Category.findAll();
            render(categories);
        }
    }

}