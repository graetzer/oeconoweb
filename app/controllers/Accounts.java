package controllers;

import java.util.List;
import models.Account;
import models.Transaction;
import play.mvc.Controller;
import play.i18n.Messages;
import play.data.validation.Validation;
import play.data.validation.Valid;


public class Accounts extends BaseController {
	public static void index() {
		List<Account> entities = models.Account.find("byUser", Security.connectedUser()).fetch();
		render(entities);
	}

	public static void create(Account entity) {
		render(entity);
	}

	public static void show(java.lang.Long id) {
		Account entity = Account.findById(id);
	    if (entity.user.equals(Security.connectedUser()))
			render(entity);
	    else
	    	badRequest();
	}

	public static void edit(java.lang.Long id) {
		Account entity = Account.findById(id);
	    if (entity.user.equals(Security.connectedUser()))
			render(entity);
	    else
	    	badRequest();
	}

	public static void delete(java.lang.Long id) {
		Account entity = Account.findById(id);
		
	    if (entity.user.equals(Security.connectedUser())) {
	    	entity.delete();
	    	index();
	    } else
	    	badRequest();
	}
	
	public static void save(@Valid Account entity) {
		if (validation.hasErrors()) {
			flash.error(Messages.get("scaffold.validation"));
			render("@create", entity);
		}
		entity.save();
		flash.success(Messages.get("scaffold.created", "Account"));
		index();
	}

	public static void update(@Valid Account entity) {
		if (validation.hasErrors()) {
			flash.error(Messages.get("scaffold.validation"));
			render("@edit", entity);
		}
		
      	entity = entity.merge();
		entity.save();
		flash.success(Messages.get("scaffold.updated", "Account"));
		index();
	}

	public static void trasactions(Long id) {
		Account a = Account.findById(id);
		if (a != null) {
			List<Transaction> entities = Transaction.find("byAccount", a).fetch();
			renderTemplate("Transactions/index.html", entities);
		}
		badRequest();
	}
}
