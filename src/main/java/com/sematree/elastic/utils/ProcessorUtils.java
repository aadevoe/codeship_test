package com.sematree.elastic.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProcessorUtils {
	public static long getWorkingDaysBetweenTwoDates(Date start, Date end) {
		Calendar c1 = Calendar.getInstance();
	    c1.setTime(start);
	    int w1 = c1.get(Calendar.DAY_OF_WEEK);
	    c1.add(Calendar.DAY_OF_WEEK, -w1);

	    Calendar c2 = Calendar.getInstance();
	    c2.setTime(end);
	    int w2 = c2.get(Calendar.DAY_OF_WEEK);
	    c2.add(Calendar.DAY_OF_WEEK, -w2);

	    //end Saturday to start Saturday 
	    long days = (c2.getTimeInMillis() - c1.getTimeInMillis()) / (1000 * 60 * 60 *24);
	    long daysWithoutWeekendDays = days-(days * 2 / 7);

	    // Adjust w1 or w2 to 0 since we only want a count of *weekdays*
	    // to add onto our daysWithoutWeekendDays
	    if (w1 == Calendar.SUNDAY) {
	        w1 = Calendar.MONDAY;
	    }

	    if (w2 == Calendar.SUNDAY) {
	        w2 = Calendar.MONDAY;
	    }
	    
	    long totalWorkingDays = daysWithoutWeekendDays - w1 + w2 - 1; // Offset by 1 per Intertek's request
	    
	    if (totalWorkingDays < 0) {
	    	totalWorkingDays = 0;
	    }

	    return totalWorkingDays;
	}
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String startDateString = "2015-05-01";
		String endDateString = "2015-05-05";
		
		Date startDate = null;
		Date endDate = null;
		
		try {
			startDate = sdf.parse(startDateString);
			endDate = sdf.parse(endDateString);
		}catch (Exception e) {
			System.err.println("Error parsing dates: ");
			e.printStackTrace();
		}
		
		long numOfDays = ProcessorUtils.getWorkingDaysBetweenTwoDates(startDate, endDate);
		
		System.out.println("Number of working days: " + numOfDays);
	}
}
