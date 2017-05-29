package models;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Page;

import play.data.format.Formats;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;


@Entity
public class Ticket extends Model {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	public Long id;
	
	@Required
	public String name;
	
	public String category;
	
	public Integer price;
	
	@Formats.DateTime(pattern="yyyy-MM-dd")
	public Date introduced;
	
	@Formats.DateTime(pattern="yyyy-MM-dd")
	public Date discontinued;
	
	@ManyToOne
	public Company company;
	
	public static Finder<Long,Ticket> find = new Finder<Long,Ticket>(Long.class, Ticket.class);
	
	/**
	 * Return a page of ticket
	 * 
	 * @param page 表示するページ
	 * @param pageSize 1ページあたりの表示件数
	 * @param sortBy ソートに使用されるプロパティ名
	 * @param order 昇順asc か 降順desc か
	 * @param category どのカテゴリーか
	 * @param filter 検索文字列
	 * @return
	 */
	public static Page<Ticket> page(int page, int pageSize, String sortBy, String order, String filter) {
		return 
			find.where()
				.ilike("name", "%" + filter + "%")
				.orderBy(sortBy + " " + order)
				.fetch("company")
				.findPagingList(pageSize)
				.getPage(page);
	}
	
	/**
	 * Return a category page of ticket
	 * 
	 * @param page 表示するページ
	 * @param pageSize 1ページあたりの表示件数
	 * @param sortBy ソートに使用されるプロパティ名
	 * @param order 昇順asc か 降順desc か
	 * @param category どのカテゴリーか
	 * @return
	 */
	public static Page<Ticket> categoryPage(int page, int pageSize, String sortBy, String order, String category) {
		return 
			find.where()
				.ilike("category", category)
				.orderBy(sortBy + " " + order)
				.fetch("company")
				.findPagingList(pageSize)
				.getPage(page);
	}
	
	
	// idの最大値を取得するメソッド
	public static int getMaxId() {
    	return find.findRowCount();
    }
	
	// IDを引数にとり、そのIDに該当するチケットのレコードを取り出して、返り値として返すメソッド
	public static Ticket getTicketRecordById(long id) {
		return find.byId(id);
	}
	
	
	
	
	
	
}


