package Dates;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class toTimestamp {
    public static String timeCurrent() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String formattedDateTime = formatter.format(now);
//        System.out.println("Thời gian hiện tại định dạng là: " + formattedDateTime);
        return (String) formattedDateTime;
    }

    public static Timestamp toTimeStamp(String date) {
        String[] text = date.split("/");
        String temp = text[0];
        System.out.println("check date :" + date);
        System.out.println("Check length:" + text.length);
        text[0] = text[2];
        text[2] = temp;
        date = "";
        for (int i = 0; i < text.length; i++) {
            date = date + text[i];
            if (i != text.length - 1) {
                date = date + "-";
            }
        }

        date = date + " " + timeCurrent();
        Timestamp timestamp = Timestamp.valueOf(date);
//        System.out.println(timestamp);
        return timestamp;
    }

    public static String toStringTimeStamp(String date) {
        try {
            String[] text = date.split("/");
            String temp = text[0];
            System.out.println("check date :" + date);
            System.out.println("Check length:" + text.length);
            text[0] = text[2];
            text[2] = temp;
            date = "";
            for (int i = 0; i < text.length; i++) {
                date = date + text[i];
                if (i != text.length - 1) {
                    date = date + "-";
                }
            }
            date = date+" 00:00:00";
        } catch (Exception e) {
            System.out.println("Error at toStringTimeStamp" + e.getMessage());
        }
        return date;
    }
}
