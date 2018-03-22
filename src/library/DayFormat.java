package library;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DayFormat {
	public static String fD(Timestamp Tdate) {
		Date date = new Date(Tdate.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dateF = sdf.format(date);
		return dateF;
	}
	public static String fD(String Ddate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		Date startDate;
		String newDateString = "";
		try {
		    startDate = df.parse(Ddate);
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    newDateString = sdf.format(startDate);
		    System.out.println("Day mới: "+newDateString);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
//		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//		String DateF = "";
//		try {
//		    Date date = formatter.parse(Ddate);
//		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		    DateF = sdf.format(date);
//		} catch (ParseException e) {
//		    e.printStackTrace();
//		}
		return newDateString;
	}
}
