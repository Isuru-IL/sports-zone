package lk.ijse.sports_zone.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import javafx.scene.control.Control;
import org.apache.commons.validator.routines.EmailValidator;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.controlsfx.control.WorldMapView.Country.LK;

public class ValidateController {
    public static boolean emailCheck(String email){
//        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
//        Pattern pattern = Pattern.compile(emailRegex);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();

        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }

    public static boolean contactCheck(String contact){
//        String contactRegex = "^(?:7|0|(?:\\\\\\\\+94))[0-9]{9,10}$";
//        Pattern pattern = Pattern.compile(contactRegex);
//        Matcher matcher = pattern.matcher(contact);
//        return matcher.matches();

        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = null;
        try {
            phoneNumber = phoneUtil.parse(contact, String.valueOf(LK));
        } catch ( NumberParseException e) {
            // The input is not a valid phone number
            return false;
        }
        boolean isValid = phoneUtil.isValidNumber(phoneNumber);
        return isValid;
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
        Pattern patternNew = Pattern.compile("^([0-9]{2})(0[1-9]|1[0-2])([0-3][0-9])([0-9]{4})([0-9]{4})([vVxX])?$");
        Pattern patternOld = Pattern.compile("^[0-9]{9}[vVxX]$");
        Pattern patternForeign = Pattern.compile("^[0-9]{12}$");

        Validator<String> validator = new Validator<String>() {
            @Override
            public ValidationResult apply(Control control, String value) {
                boolean matchesNew = patternNew.matcher(value).matches();
                boolean matchesOld = patternOld.matcher(value).matches();
                boolean matchesForeign = patternForeign.matcher(value).matches();
                boolean matches = matchesNew || matchesOld || matchesForeign;
                return ValidationResult.fromMessageIf(control, "Invalid NIC", Severity.ERROR, !matches);
            }
        };

        ValidationResult result = validator.apply(null, nic);
        return result.getErrors().isEmpty();

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

    public static boolean employeeIdCheck(String empId) {
        String pattern = "^E-\\d+$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(empId);
        return m.matches();
    }

    public static boolean yearCheck(String input) {
        if (input.matches("\\d{4}")) {
            return true; // Input contains only numeric characters
        } else {
            return false; // Input contains non-numeric characters
        }
    }
}
