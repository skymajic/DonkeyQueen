package models;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Company extends Model {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	public Long id;
	
	@Required
	public String name;
	
	public static Finder<Long,Company> find = new Finder<Long,Company>(Long.class, Company.class);
	
	// チケット追加時の、セレクトボックスでの会社の名前選択
	public static Map<String, String> options() {
		Map<String,String> options = new HashMap<String,String>();
		for(Company c: Company.find.orderBy("name").findList()) {
			options.put(c.id.toString(), c.name);
		}
		return options;
	}
	
}




