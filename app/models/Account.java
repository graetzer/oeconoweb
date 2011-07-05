package models;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.*;
import play.db.jpa.Model;

@Entity
public class Account extends Model {
	@Required
	@ManyToOne
	public User user;
	
	public String bankname;
	
	@Required
	@Column(unique=true)
	public String number;
	
	@Required
	public double startBalance;
	
	@Required
	public Date startDate;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="account")
	public List<Transaction> transactions;
	
	@Override
	public String toString() {
		return bankname + ": "+number;
	}
	
	public double actualBalance() {
	    double balance = startBalance;
	    for (Transaction t : this.transactions) {
	        balance += t.value;
	    }
	    return balance;
	}
}
