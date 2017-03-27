import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;


public class testdate {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		 Date date = new Date();
		 int effectiveDays = 7;
         Date modifideDate = (Date) DateUtils.addDays(date, (effectiveDays * -1));
         SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
         
         //445555555  1470300118807  1481088170108
         Long time=new Long(1481088170);  
         String d = format.format(time);  
         Date date1=format.parse(d);  
         System.out.println("Format To String(Date):"+d);  
         System.out.println("Format To Date:"+date1);
         
         //1479955555000  1479231154924
         SimpleDateFormat format1 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
         String time1="2016-11-24 10:45:55";//注：改正后这里前后也加了空格  
         Date date2 = format1.parse(time1);  
         System.out.print("Format To times:"+date2.getTime());  
	}

}
