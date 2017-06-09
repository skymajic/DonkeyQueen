package controllers;

import static play.data.Form.form;

import models.Ticket;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Admin extends Controller{
	
    
	/**
	 * リターンの返り値に、Go_Admin_Homeを指定すると、manage.scala.htmlにリダイレクトされる
     */
	public static Result GO_Admin_Home = redirect(
		routes.Admin.manage(0, "name", "asc", "")
	);

	/**
	* スラッシュmanageIndexにアクセスすると、manage.scala.htmlにリダイレクト
	* @return
	*/
	public static Result manageIndex() {
		return GO_Admin_Home;
	}
	
	/**
	 * Display the full range of tickets.
	 * 
	 * @param page    現在のページ番号
	 * @param sortBy  ソートに使用されるカラム
	 * @param order   昇順か降順か
	 * @param filter  検索文字列
	 * @return
	 */
	public static Result manage(int page, String sortBy, String order, String filter) {
		return ok(
				manage.render(
						Ticket.page(page, 10, sortBy, order, filter),sortBy,order,filter
				)
		);
	}
	
	
	
	/**
	 * Display the edit form of a clicked Ticket.
	 * 
	 * @param id
	 * @return
	 */
	public static Result edit(Long id){
		Form<Ticket> ticketForm = form(Ticket.class).fill(
		    Ticket.find.byId(id)
	    );
		
		return ok(
			editForm.render(id, ticketForm)
		);
	}
	
	
	/**
	 * Handle the edit form submission.
	 * 
	 * @param id
	 * @return
	 */
	public static Result update(Long id) {
		Form<Ticket> ticketForm = form(Ticket.class).bindFromRequest();
		
		if(ticketForm.hasErrors()) {
			return badRequest(editForm.render(id, ticketForm));
		}
		
		ticketForm.get().update(id);
		
		flash("success", "Ticket " + ticketForm.get().name + " has been updated");
		
		return GO_Admin_Home;
	}
	
	
    
   
	/**
	 * Display the new ticket form.
	 * @return
	 */
	public static Result create() {
		Form<Ticket> ticketForm = form(Ticket.class);
		return ok(
				createForm.render(ticketForm)
		);
	}
	
	/**
	 * Save the new ticket form submission.
	 * @return
	 */
	public static Result save() {
		
		Form<Ticket> ticketForm = form(Ticket.class).bindFromRequest();
		// idの最大値をDBから検索して、それをインクリメントする
		ticketForm.get().id = (long)(Ticket.getMaxId() + 1);
		if(ticketForm.hasErrors()) {
			return badRequest(createForm.render(ticketForm));
		}
		ticketForm.get().save();
		flash("success", "Ticket " + ticketForm.get().name + " has been created");
		return GO_Admin_Home;
		
	}
	
    
	/**
	 * Delete the ticket designated by id.
	 * 
	 * @param id
	 * @return
	 */
	public static Result delete(Long id) {
		Ticket.find.ref(id).delete();
		flash("success", "Ticket has been deleted");
		return GO_Admin_Home;
	}

}



    