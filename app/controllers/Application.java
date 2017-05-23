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
    	return ok(rarara.render("This is a rarara."));
    }
    
    public static Result register() {
    	return ok(register.render("新規会員登録情報を入力してください。"));
    }
    
    public static Result login() {
    	return ok(login.render("ログイン情報を入力してください。"));
    }
    
    public static Result individualGoods() {
    	return ok(individualGoods.render("チケット情報"));
    }
    
    public static Result buy() {
    	return ok(buy.render("個別商品購入画面です。"));
    }
    
    public static Result finished() {
    	return ok(finished.render("個別商品購入完了画面です。"));
    }
    
    

}
