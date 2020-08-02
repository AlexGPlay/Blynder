package org.blynder.core.logging;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Locale;

/**
 * PrintStream that will use the Logging class in order to print info to the
 * outputstream or the error stream.
 */
public class CustomPrintStream extends PrintStream{

	private Logging.LOG_LEVELS logLevel;
	
	public CustomPrintStream(Logging.LOG_LEVELS logLevel) throws FileNotFoundException{
		super(logLevel + "_log.log");
		this.logLevel = logLevel;
	}
	
	public void print(boolean o) {
		log(o);
	}
	
	public void print(char o) {
		log(o);
	}
	
	public void print(char[] o) {
		log(o);
	}
	
	public void print(double o) {
		log(o);
	}
	
	public void print(float o) {
		log(o);
	}
	
	public void print(int o) {
		log(o);
	}
	
	public void print(long o) {
		log(o);
	}
	
	public void print(Object o) {
		log(o);
	}
	
	public void print(String o) {
		log(o);
	}
	
	public PrintStream printf(Locale l, String format, Object... args){
		log(String.format(format, args));
		return this;
	}
	
	public PrintStream printf(String format, Object... args){
		log(String.format(format, args));
		return this;
	}
	
	public void println(boolean o) {
		log(o);
	}
	
	public void println(char o) {
		log(o);
	}
	
	public void println(char[] o) {
		log(o);
	}
	
	public void println(double o) {
		log(o);
	}
	
	public void println(float o) {
		log(o);
	}
	
	public void println(int o) {
		log(o);
	}
	
	public void println(long o) {
		log(o);
	}
	
	public void println(Object o) {
		log(o);
	}
	
	public void println(String o) {
		log(o);
	}
	
	private int log(Object o) {
		return logLevel.equals(Logging.LOG_LEVELS.INFO) ? Logging.info(o.toString()) : Logging.error(o.toString());
	}

}
