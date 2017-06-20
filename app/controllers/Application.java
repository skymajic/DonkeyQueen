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
	
	/**
	 * ログインオブジェクト
	 */
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
	    if(loginForm.hasErrors()) {
	       return badRequest(login.render("ログイン情報を入力してください。",loginForm));
	    } else {
	       session("name", loginForm.get().name);
	       session("password", loginForm.get().password);
	       return redirect(
	          routes.Application.index(0,"name","asc","")
	       );
	    }
	 }
	    
	 /**
	  * マイページにかける認証
	  * @return
	  */
	 public static Result myPageAuthenticate() {
	    Form<Login> loginForm = form(Login.class).bindFromRequest();
	    if(loginForm.hasErrors()) {
	       return badRequest(myPageLogin.render("ログイン情報を入力してください。",loginForm));
	    } else {
	       session("name", loginForm.get().name);
	       session("password", loginForm.get().password);
	       return redirect(
	    	  routes.Application.myPage()
	       );
	    }
	    	
	 }
	    
	   
	 public static Result login() {
	    return ok(login.render("ログイン情報を入力してください。",form(Login.class)));
	 }
	    
	 public static Result myPageLogin() {
	    return ok(myPageLogin.render("ログイン情報を入力してください。",form(Login.class)));
	 }
	    
	 /**
	  * Logout and clean the session.
	  */
	 public static Result logout() {
	    session().clear();
	    flash("success", "You've been logged out");
	    return redirect(
	       routes.Application.index(0,"name","asc","")
	    );
	 }
	
	/**
	 * Home page
	 * @param page
	 * @param sortBy
	 * @param order
	 * @param filter
	 * @return
	 */
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
    
    /**
	 * 新規登録画面
	 * @return
	 */
	public static Result register() {
		Form<User> f = new Form(User.class);
		return ok(register.render("新規会員登録情報を入力してください。", f));
	}
	
	public static Result userCreate() {
		Form<User> f = new Form(User.class).bindFromRequest();		
		User data = f.get();
		
		if (User.findByName(data.name) != null) {
			
			return badRequest(register.render("そのユーザ名は既に登録されています",f));
			
		} else if(!f.hasErrors()) {
		
			data.save();
			return redirect("/");
		
		} else {
		
			return badRequest(register.render("ERROR", f));
		
		}
		
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
    	Map<String,String[]> form = request().body().asFormUrlEncoded();
    	String[] ticketID = form.get("ticketID");   	
    	long id = Long.parseLong(ticketID[0]);
    	Ticket ticket = Ticket.getTicketRecordById(id);
      	return ok(individualGoods.render(ticket.name,ticket.category,ticket.price,ticket.introduced,ticket.discontinued,ticket.id));	
    }
    
    
    public static Result buy() {
    	Map<String,String[]> form = request().body().asFormUrlEncoded();
    	String[] ticketID = form.get("ticketID");   	
    	long id = Long.parseLong(ticketID[0]);
    	Ticket ticket = Ticket.getTicketRecordById(id);
    	return ok(buy.render("商品検索","個別商品","購入","購入完了",ticket));
    }
    
    
    public static Result finished() {
    	return ok(finished.render("商品検索","個別商品","購入","購入完了","購入を完了しました。"));
    } 
    
    /**
     * マイページ
     * @return
     */
    @Security.Authenticated(MyPageSecured.class)
    public static Result myPage() {
    	return ok(myPage.render("さんのアカウント情報です"));
    }

}
