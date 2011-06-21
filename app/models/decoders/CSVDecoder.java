package models.decoders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;

import controllers.Security;

import play.Logger;

import models.Account;
import models.User;
import models.Transaction;

public class CSVDecoder extends DecoderImpl {
	public CSVDecoder(Reader source) {
		super(source);
	}
	
	public CSVDecoder(File source) throws FileNotFoundException {
		super(source);
	}

	@Override
	public List<Transaction> decode() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		CsvListReader reader = new CsvListReader(source, CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
		try {
			String[] header = reader.getCSVHeader(true);
			for (String h : header)
				Logger.debug("Columstitle: %s", h);
			
			Account acc = null;
			DateFormat df = new SimpleDateFormat("dd.MM.yy");
			List<String> cols;
			while((cols = reader.read()) != null) {
				if (cols.size() < 11)
					throw new IllegalArgumentException();
				
				/* Should only executed on first iteration */
				if(acc == null) {
					String number = cols.get(0).trim();
					acc = Account.find("byNumber", number).first();
					if(acc == null) {
						acc = new Account();
						acc.number = number;
						acc.startBalance = 0;
						acc.startDate = new Date();
						acc.user = Security.connectedUser();
						acc.save();
					}
				}
				
				Transaction tr = new Transaction();
				tr.account = acc;
				tr.value = Double.parseDouble(cols.get(8).trim().replace(',', '.'));
				tr.date = df.parse(cols.get(2).trim());
				tr.mode = cols.get(3);
				tr.text = cols.get(4);
				tr.involved = cols.get(5);
				tr.save();
				transactions.add(tr);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactions;
	}
}
