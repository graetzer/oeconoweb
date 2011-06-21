package models.matcher;

import java.util.ArrayList;
import java.util.List;

import models.Rule;
import models.Transaction;

import controllers.Rules;

public class Matcher {
	List<Rule> rules;
	
	final int MIN_TRESHOLD = 0;

	public Matcher(List<Rule> rules) {
		super();
		this.rules = rules;
	}

	public void match(List<Transaction> ts) {
		if (ts == null || rules == null)
			throw new IllegalArgumentException("An argument is null!!");

		for (Transaction t : ts) {
			int maxId = 0;
			int maxPoints = 0;

			// Try each rule on the transaction and aggregate the weight. then choose the heaviest
			for (int i = 0; i < rules.size(); i++) {
				Rule r = rules.get(i);
				int points = 0;

				points += r.match(t.text);
				points += r.match(t.mode);
				points += r.match(t.involved);

				if (points > maxPoints)
					maxId = i; maxPoints = points;
			}
			
			//Save the result
			if (MIN_TRESHOLD < maxPoints)
			    t.category = rules.get(maxId).category;
			
			t.save();
		}
	}

}
