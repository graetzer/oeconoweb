package controllers;

import java.util.List;
import models.Category;
import play.mvc.Controller;
import play.i18n.Messages;
import play.data.validation.Validation;
import play.data.validation.Valid;


public class Categories extends BaseController {
	public static void index() {
		List<Category> entities = models.Category.all().fetch();
		render(entities);
	}

	public static void create(Category entity) {
		render(entity);
	}

	public static void show(java.lang.Long id) {
    Category entity = Category.findById(id);
		render(entity);
	}

	public static void edit(java.lang.Long id) {
    Category entity = Category.findById(id);
		render(entity);
	}

	public static void delete(java.lang.Long id) {
    Category entity = Category.findById(id);
    entity.delete();
		index();
	}
	
	public static void save(@Valid Category entity) {
		if (validation.hasErrors()) {
			flash.error(Messages.get("scaffold.validation"));
			render("@create", entity);
		}
		entity.save();
		flash.success(Messages.get("scaffold.created", "Category"));
		index();
	}

	public static void update(@Valid Category entity) {
		if (validation.hasErrors()) {
			flash.error(Messages.get("scaffold.validation"));
			render("@edit", entity);
		}
		entity = entity.merge();
		entity.save();
		flash.success(Messages.get("scaffold.updated", "Category"));
		index();
	}

}
