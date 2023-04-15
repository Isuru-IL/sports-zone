package lk.ijse.sports_zone.util;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateController {
    public static boolean emailCheck(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean contactCheck(String contact){
        String contactRegex = "^(?:7|0|(?:\\\\\\\\+94))[0-9]{9,10}$";
        Pattern pattern = Pattern.compile(contactRegex);
        Matcher matcher = pattern.matcher(contact);
        return matcher.matches();
    }

    public static boolean doubleValueCheck(String text) {
//        String contactRegex = "^-?\\d+(?:\\.\\d+)?$";
//        Pattern pattern = Pattern.compile(contactRegex);
//        Matcher matcher = pattern.matcher(text);
//        return matcher.matches();
        if(text.matches("^-?\\d+(?:\\.\\d+)?$")){
            return true;
        }else {
            return false;
        }
    }

    public static boolean NICcheck(String nic) {
        String nicRegex = "^[0-9]{9}[vVxX]$";
        Pattern pattern = Pattern.compile(nicRegex);
        Matcher matcher = pattern.matcher(nic);
        return matcher.matches();
    }

    public static boolean intValueCheck(String value) {
        String intRegex = "^-?[0-9]+$";
        Pattern pattern = Pattern.compile(intRegex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static boolean dateCheck(String value) {
        String dateRegex = "\\\\d{4}-\\\\d{2}-\\\\d{2}";
        Pattern pattern = Pattern.compile(dateRegex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
