package controllers;

import models.*;
import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
        
    public static Result index(int page, String sortBy, String order, String filter) {
    	return ok(
    		index.render(Ticket.page(page, 9, sortBy, order, filter),sortBy, order, filter)	
    	);
    }
    
    // サンプルページ
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
    	return ok(buy.render("商品検索","個別商品","ログイン","購入","購入完了"));
    }
    
    public static Result finished() {
    	return ok(finished.render("商品検索","個別商品","ログイン","購入","購入完了","購入を完了しました。"));
    } 

}
