package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import models.Rule;
import models.Transaction;
import models.decoders.CSVDecoder;
import models.decoders.Decoder;
import models.matcher.Matcher;

import play.data.validation.Required;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Importer extends Controller {
	@Before
	public static void secure() {
		renderArgs.put("user", Security.connectedUser());
	}
	
	public static void index() {
		render();
	}
	
	public static void decode(File attachment) throws FileNotFoundException {
		if (attachment != null) {
			checkAuthenticity();
			Decoder d = new CSVDecoder(attachment);
			List<Transaction> transactions = d.decode();
			
			List<Rule> rules = Rule.find("byUser", Security.connectedUser()).fetch();
			if (rules != null && rules.size() > 0) {
			    Matcher m = new Matcher(rules);
			    m.match(transactions);
			}
			
			Transactions.show(transactions.get(0).id);
		}
		index();
	}
}
