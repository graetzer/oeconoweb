package models.decoders;

import java.io.BufferedReader;
import java.io.File;
import java.io.Reader;
import java.util.List;

import models.Account;
import models.Transaction;

public interface Decoder {
	public List<Transaction> decode ();
}
