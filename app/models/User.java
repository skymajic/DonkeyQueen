package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.format.*;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
@Table(name="account")
public class User extends Model {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Required
	@Formats.NonEmpty
	public String name;
	
	@Required
	public String password;
	
	public static Finder<String,User> find = new Finder<String,User>(String.class, User.class);
	
	/**
	 * Retrieve a user from email.
	 * @param email
	 * @return
	 */
	public static User findByName(String name) {
		return find.where().eq("name", name).findUnique();
	}
	
	/**
	 * Authenticate a User
	 * @param name
	 * @param password
	 * @return
	 */
	public static User authenticate(String name, String password) {
		return find.where()
				.eq("name", name)
				.eq("password", password)
				.findUnique();
	}

}
