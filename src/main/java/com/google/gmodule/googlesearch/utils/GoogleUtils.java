package com.google.gmodule.googlesearch.utils;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class GoogleUtils {

	/**
	 * constructor to initialize google utils objects
	 */
	public GoogleUtils() {
	}

   /**
    * method to get current time stamp
    * @return String
    */
   public String getCurrentTimestamp() {
	    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	   return sdf.format(timestamp);
   }
}
