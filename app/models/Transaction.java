package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Transaction extends Model {
	@Required
	public double value;

	@Required
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	public Account account;

	@Required
	public Date date;

	public String mode;

	public String text;

	public String involved;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	public Category category;

	@Override
	public String toString() {
		return date.toString() + "  " + value;
	}
}
