package models.decoders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import models.Account;
import models.Transaction;

public abstract class DecoderImpl implements Decoder{
	protected Reader source;
	
	public DecoderImpl(File source) throws FileNotFoundException {		
		this(new BufferedReader(new FileReader(source)));
	}
	
	public DecoderImpl(Reader source) {
		super();
		if (source == null)
			throw new IllegalArgumentException("A parameter is null!!");
		this.source = source;
	}
}
