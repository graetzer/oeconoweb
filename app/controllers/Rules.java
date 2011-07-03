package controllers;

import java.util.List;
import models.Rule;
import play.mvc.Controller;
import play.i18n.Messages;
import play.data.validation.Validation;
import play.data.validation.Valid;


public class Rules extends BaseController {
	public static void index() {
		List<Rule> entities = models.Rule.all().fetch();
		render(entities);
	}

	public static void create(Rule entity) {
		render(entity);
	}

	public static void show(java.lang.Long id) {
    Rule entity = Rule.findById(id);
		render(entity);
	}

	public static void edit(java.lang.Long id) {
    Rule entity = Rule.findById(id);
		render(entity);
	}

	public static void delete(java.lang.Long id) {
    Rule entity = Rule.findById(id);
    entity.delete();
		index();
	}
	
	public static void save(@Valid Rule entity) {
		if (validation.hasErrors()) {
			flash.error(Messages.get("scaffold.validation"));
			render("@create", entity);
		}
		
		if (entity.user == null)
			entity.user = Security.connectedUser();
		
		entity.save();
		flash.success(Messages.get("scaffold.created", "Rule"));
		index();
	}

	public static void update(@Valid Rule entity) {
		if (validation.hasErrors()) {
			flash.error(Messages.get("scaffold.validation"));
			render("@edit", entity);
		}
		entity = entity.merge();
		entity.save();
		flash.success(Messages.get("scaffold.updated", "Rule"));
		index();
	}

}
