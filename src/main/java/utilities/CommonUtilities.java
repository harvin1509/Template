package utilities;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.DateFormat;
import javax.xml.parsers.ParserConfigurationException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CommonUtilities {
        static Document doc,doc1;
    public static String getTimeStampString(String format){
        Date dNow=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        String datetime=sdf.format(dNow);
        return datetime;
    }

    public static int countNumberOfFilesinFolder(File folder) {
        int count=0;
        File[] files=folder.listFiles();
        for(File file:files){
            if(file.isFile()){
                count++;
            }
        }
        return count;
    }

    public static String getDateBasedOnCharacterBeforeWorkingDays(String dataPublishedday, String requiredFormat) {
        if(!(dataPublishedday.equalsIgnoreCase("D"))){
            String dateValue=dataPublishedday;
            String[] DateSplit=dateValue.split("\\-");
            int noOfFutureDays=Integer.parseInt(DateSplit[1]);

            return getDateBasedOnCharacterBeforeWorkingDays(getTimeStampString("dd/MM/yyyy"), "dd/MM/yyyy" , requiredFormat, noOfFutureDays);
        } else if (dataPublishedday.equalsIgnoreCase("D")) {
            Date today=new Date();
            DateFormat dateFormat=new SimpleDateFormat(requiredFormat);
            return dateFormat.format(today).toString();
        }else
            return null;
    }

    private static String getDateBasedOnCharacterBeforeWorkingDays(String timeStampString, String oldDateFormat, String requiredFormat, int noOfFutureDays) {
        DateFormat oldFormat=new SimpleDateFormat(oldDateFormat, Locale.ENGLISH);
        DateFormat newFormat= new SimpleDateFormat(requiredFormat, Locale.ENGLISH);
        Calendar calendar= Calendar.getInstance();
        Date date=null;
        try{
            date=oldFormat.parse(timeStampString);
            calendar.setTime(date);
            for(int i=0; i<noOfFutureDays;){
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                if(calendar.get(Calendar.DAY_OF_WEEK) >1 && calendar.get(Calendar.DAY_OF_WEEK)<=6){
                    i++;
                }
            }
            date=calendar.getTime();
            return newFormat.format(date);
        }
        catch(Exception e){
            e.printStackTrace();
            return "DateFormatException";
        }
    }

    public static Document initializeXMLDocument(File objFile) {
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {

                long len=objFile.length();
                if(len!=0){
                    documentBuilder=documentBuilderFactory.newDocumentBuilder();
                    doc1=documentBuilder.parse(objFile);
                    doc1.getDocumentElement().normalize();
                }
                 doc=doc1;
                return doc;

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;

        }
    }
}
