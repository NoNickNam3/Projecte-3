package org.milaifontanals.projecte3.utils.comprovacions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComprovacionsUtil {

    public static Boolean isNomCognomValid(String nom){
        return nom.matches("[A-Z][a-z]*");
    }

    public static boolean isValidPassword(String password) {
        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
        if (password == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);

        // Return if the password
        // matched the ReGex
        return m.matches();
    }

    public static String getStringNN(Object o){

        if(o == null){
            return "";
        }

        return o.toString();
    }

}
