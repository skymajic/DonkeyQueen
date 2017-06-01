package controllers;

import static play.data.Form.form;

import java.util.Map;

import com.avaje.ebean.Page;

import controllers.Application.Login;
import models.*;
import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
	
	 public static class Login {
	        
	        public String name;
	        public String password;
	        
	        public String validate() {
	            if(User.authenticate(name, password) == null) {
	                return "Invalid user or password";
	            }
	            return null;
	        }
	        
	  }
	 
	 /**
	     * Handle login form submission.
	     */
	    public static Result authenticate() {
	        Form<Login> loginForm = form(Login.class).bindFromRequest();
	        System.out.println("デバッグでござる" + loginForm.get().name + " " + loginForm.get().password);
	        if(loginForm.hasErrors()) {
	            return badRequest(login.render("ログイン情報を入力してください。",loginForm));
	        } else {
	            session("name", loginForm.get().name);
	            return redirect(
	                routes.Application.index(0,"name","asc","")
	            );
	        }
	    }
	    
	   
	    public static Result login() {
	        return ok(login.render("ログイン情報を入力してください。",form(Login.class)));
	    }
	 
	
	
    
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
    
    
    /**
     * 1. viewsのindex.scala.htmlから、チケットIDを受け取る。フォームヘルパーを使用する。actionはindividualGoods()
     * 2. 1で受け取ったチケットIDを引数にして、modelsのTicket.javaのメソッドを使い、該当するチケットのデータを取り出す
     * 3. 2で取り出したチケットのデータをこのApplication.javaのokメソッドの引数に割り当てる
     * 4. viewsのindividualGoods.scala.htmlのファイル最上部で、individualGoods()メソッドの戻り値を受け取る
     * 5. viewsのindividualGoods.scala.htmlのファイル内で、individualGoods()メソッドの戻り値を一つ一つ出力する
     * @return
     */
    public static Result individualGoods() {
    	// index.scala.htmlのフォームヘルパーから、idを受け取る処理。bindFromRequestのような処理
    	Map<String,String[]> form = request().body().asFormUrlEncoded();
    	String[] ticketID = form.get("ticketID");   	
    	long id = Long.parseLong(ticketID[0]);
    	Ticket ticket = Ticket.getTicketRecordById(id);
    	return ok(individualGoods.render(ticket.name,ticket.category,ticket.price,ticket.introduced,ticket.discontinued,ticket.id));
    }
    
    @Security.Authenticated(Secured.class)
    public static Result buy() {
    	Map<String,String[]> form = request().body().asFormUrlEncoded();
    	String[] ticketID = form.get("ticketID");   	
    	long id = Long.parseLong(ticketID[0]);
    	Ticket ticket = Ticket.getTicketRecordById(id);
    	return ok(buy.render("商品検索","個別商品","購入","購入完了",ticket));
    }
    
    @Security.Authenticated(Secured.class)
    public static Result finished() {
    	return ok(finished.render("商品検索","個別商品","購入","購入完了","購入を完了しました。"));
    } 
    
    // マイページ
    @Security.Authenticated(Secured.class)
    public static Result myPage() {
    	return ok(myPage.render("ゲスト"));
    }
    
    public static Result boughtTicketHistory() {
    	return ok(boughtTicketHistory.render("チケット購入履歴です"));
    }
    
    public static Result accountInfo() {
    	return ok(accountInfo.render("アカウント情報です"));
    }
    
    public static Result miniGame() {
    	return ok(miniGame.render("ミニゲームです"));
    }

}
