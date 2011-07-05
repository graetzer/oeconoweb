package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public abstract class BaseController extends Controller {
	
    @Before
    public static void secure() {
    	// Add user to renderArgs
        Security.connectedUser();
    }

}
