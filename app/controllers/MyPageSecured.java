package controllers;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

public class MyPageSecured extends Security.Authenticator {
	
	@Override
	public String getUsername(Context ctx) {
		return ctx.session().get("name");
	}
	
	@Override
	public Result onUnauthorized(Context ctx) {
		return redirect(routes.Application.myPageLogin());
	}
	

}

