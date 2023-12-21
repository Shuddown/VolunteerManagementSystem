package common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Utils {
    private static final Scanner INPUT = new Scanner(System.in);

    public static LocalDateTime inputDateTime(String dateMessage, String timeMessage, DateTimeFormatter format) {
        LocalDateTime l;
        while (true) {
            System.out.print(dateMessage);
            String date = INPUT.nextLine();

            System.out.print(timeMessage);
            String time = INPUT.nextLine();
            try{
                l = LocalDateTime.parse(date + " " + time, format);
                break;
            }catch(DateTimeParseException e){
                System.out.println(date + " " + time + " is not in the proper format!");
            }
            
        }

        return l;

    }
}
