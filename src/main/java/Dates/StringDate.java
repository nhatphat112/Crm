package Dates;

public class StringDate {
    public static String stringTimeStampToStringDate(String date){
         // tach thanh mang String
        String [] text = date.split(" ");
        try {
            // swap dd and yyyy
            String stringDate = text[0];
             text = stringDate.split("-");
             String textTemp = text[0];
             text[0] = text[2];
             text[2] = textTemp;
             stringDate = text[0]+"/"+text[1]+"/"+text[2];
             return stringDate;

        }catch (Exception e){
            System.out.println("Error change string TimeStamp to string Date");
            e.printStackTrace();
        }
        return date;
    }
}
