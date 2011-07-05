package controllers;

import java.util.List;

import models.Rule;
import models.Transaction;
import models.matcher.Matcher;
import play.mvc.Controller;
import play.i18n.Messages;
import play.data.validation.Validation;
import play.data.validation.Valid;


public class Transactions extends BaseController {
	public static void index() {
		List<Transaction> entities = models.Transaction.all().fetch();
		render(entities);
	}

	public static void create(Transaction entity) {
		render(entity);
	}

	public static void show(java.lang.Long id) {
    Transaction entity = Transaction.findById(id);
		render(entity);
	}

	public static void edit(java.lang.Long id) {
    Transaction entity = Transaction.findById(id);
		render(entity);
	}

	public static void delete(java.lang.Long id) {
    Transaction entity = Transaction.findById(id);
    entity.delete();
		index();
	}
	
	public static void save(@Valid Transaction entity) {
		if (validation.hasErrors()) {
			flash.error(Messages.get("scaffold.validation"));
			render("@create", entity);
		}
		
		entity.save();
		flash.success(Messages.get("scaffold.created", "Transaction"));
		index();
	}

	public static void update(@Valid Transaction entity) {
		if (validation.hasErrors()) {
			flash.error(Messages.get("scaffold.validation"));
			render("@edit", entity);
		}
		
      		entity = entity.merge();
		
		entity.save();
		flash.success(Messages.get("scaffold.updated", "Transaction"));
		index();
	}

	public static void rematch() {
		List<Transaction> transactions = Transaction.findAll();
		List<Rule> rules = Rule.find("byUser", Security.connectedUser()).fetch();
		if (rules != null && rules.size() > 0) {
		    Matcher m = new Matcher(rules);
		    m.match(transactions);
		}
		
		Transactions.show(transactions.get(0).id);
	}
}
