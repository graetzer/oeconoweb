package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Rule extends Model {
	@ElementCollection
	@Column(name = "word")
	public List<String> words = new ArrayList<String>();

	@Required
	public int weight = 100;
	
	@Required
	public String name;

	@Required
	@ManyToOne(optional = false)
	public Category category;
	
	@ManyToOne(optional = false)
	public User user;
	
	@Override
	public String toString() {
	    return name;
	}

	public int match(String input) {
		int points = 0;
		String adjusted = input.toLowerCase();
		int matchPoints = weight / words.size();
		
		for (String word : words) {
			if ( adjusted.contains(word.toLowerCase()) ) {
				points += matchPoints;
			}
		}
		return points;
	}

}
