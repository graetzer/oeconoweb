package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Category extends Model {
	@Required
	public String name;
	
	@OneToMany(mappedBy="category")
	@OrderBy("date")
	public List<Transaction> transactions;
	
	@Override
	public String toString() {
		return name;
	}
	
	public double sumBalance (User user) {
	    double balance = 0;
	    List<Transaction> ts = Transaction.find("SELECT t FROM Transaction t, Account a " +
	    		"WHERE t.category.id = ? AND t.account = a AND a.user.id = ?",
	            this.id, user.id).fetch();
	    
	    for (Transaction t : ts) {
	        balance += t.value;
	    }
	    return balance;
	}
}
