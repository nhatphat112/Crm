package Dates;

public class checkDate {
        public static boolean checkDateValid(String date){
            try {
                String [] info = date.split("/");
                System.out.println("check date :"+date);
                System.out.println("check lengh: "+info.length);
                if(info.length!=3) return false;
                if(info[2].length()!=4) return false;
                int day = Integer.parseInt(info[0]);
                int month = Integer.parseInt(info[1]);
                int year = Integer.parseInt(info[2]);
                if(month<0||month>12) return false;
                switch (month){
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        if(day<1||day>31) return false;
                        break;
                    case 2:
                        if(year%400 == 0|| year%4==0&&year%100!=0){
                            if (day<1||day>29) return false;
                        } else if (day<1||day>28) return false;
                        break;
                    default:
                        if(day<1||day>30) return false;
                }


            } catch (Exception e){
                System.out.println("Error at checkDateValid");
                e.printStackTrace();
                return false;
            }
            return true;
        }

}
