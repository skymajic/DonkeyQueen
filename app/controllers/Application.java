package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    // ページ遷移サンプル
    public static Result rarara() {
    	return ok(rarara.render("This is rarara."));
    }

}
