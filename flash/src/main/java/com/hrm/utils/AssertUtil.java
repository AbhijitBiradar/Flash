package com.hrm.utils;

import java.util.logging.Logger;

public class AssertUtil {
	public static final Logger log  = Logger.getLogger(AssertUtil.class.getName());
	
	public boolean assertEqual(String expected, String actual) {
		if(expected.contentEquals(actual)) {
			log.info(expected + " and " + "are equal");
			return true;
		}
		log.info(expected + " and " + "are not equal");
		return false;
	}
	
	public boolean assertEqual(String expected, String actual, String message) {
		if(expected.contentEquals(actual)) {
			log.info(message);
			return true;
		}
		log.info(message);
		return false;
	}
	
	public boolean assertNotEqual(String expected, String actual) {
		if(!expected.contentEquals(actual)) {
			log.info(expected + " and " + "are not equal");
			return true;
		}
		log.info(expected + " and " + "are equal");
		return false;
	}
	
	public boolean assertNotEqual(String expected, String actual, String message) {
		if(!expected.contentEquals(actual)) {
			log.info(message);
			return true;
		}
		log.info(message);
		return false;
	}
	
	public boolean assertTrue(boolean condition, String message) {
		if(condition) {
			log.info(message);
			return true;
		}
		log.info(message);
		return false;		
	}	
	
	public boolean assertTrue(boolean condition) {
		if(condition) {
			log.info("condition is true");
			return true;
		}
		log.info("condition is false");
		return false;		
	}	
	
	public boolean assertFalse(boolean condition) {
		if(!condition) {
			log.info("condition is false");
			return true;
		}
		log.info("condition is true");
		return false;		
	}	
	
	public boolean assertFalse(boolean condition, String message) {
		if(!condition) {
			log.info(message);
			return true;
		}
		log.info(message);
		return false;		
	}
}
