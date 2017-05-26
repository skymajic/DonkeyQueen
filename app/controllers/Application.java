package controllers;

import com.avaje.ebean.Page;

import models.*;
import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
    
	// Home page
    public static Result index(int page, String sortBy, String order,String filter) {
    	return ok(
    		index.render(Ticket.page(page, 9, sortBy, order ,filter),sortBy, order,filter)	
    	);
    }
    
    
    /**********************
     *** Category Pages ***
     *********************/
    
    public static Result art(int page, String sortBy, String order, String category) {
    	return ok(
    		art.render(Ticket.categoryPage(page, 9, sortBy, order, category),sortBy,order,category)
    	);
    }
    

    public static Result baseball(int page, String sortBy, String order, String category) {
    	return ok(
    		baseball.render(Ticket.categoryPage(page, 9, sortBy, order, category),sortBy,order,category)
    	);
    }
    
    public static Result dubbedFilm(int page, String sortBy, String order, String category) {
    	return ok(
    		dubbedFilm.render(Ticket.categoryPage(page, 9, sortBy, order, category),sortBy,order,category)
    	);
    }
    
    public static Result japaneseMovie(int page, String sortBy, String order, String category) {
    	return ok(
    		japaneseMovie.render(Ticket.categoryPage(page, 9, sortBy, order, category),sortBy,order,category)
    	);
    }
    
    public static Result live(int page, String sortBy, String order, String category) {
    	return ok(
    		live.render(Ticket.categoryPage(page, 9, sortBy, order, category),sortBy,order,category)
    	);
    }
    
    public static Result rugby(int page, String sortBy, String order, String category) {
    	return ok(
    	    rugby.render(Ticket.categoryPage(page, 9, sortBy, order, category),sortBy,order,category)
    	);
    }
    
    public static Result soccor(int page, String sortBy, String order, String category) {
    	return ok(
    		soccor.render(Ticket.categoryPage(page, 9, sortBy, order, category),sortBy,order,category)
    	);
    }
    
    public static Result subtitledMovie(int page, String sortBy, String order, String category) {
    	return ok(
    	    subtitledMovie.render(Ticket.categoryPage(page, 9, sortBy, order, category),sortBy,order,category)
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
