package models;

import javax.persistence.Entity;

import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class User extends Model {
	@Required
	public String name;
	
	@Required
	@Email
	public String email;
	
	@Required
	public String password;
	
	public String role = "user";
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other != null && other instanceof User) {
			return ((User)other).id == this.id;
		}
		return false;
	}
}
