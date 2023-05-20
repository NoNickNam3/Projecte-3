package org.milaifontanals.projecte3.utils.comprovacions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComprovacionsUtil {

    public static Boolean isNomCognomValid(String nom){
        return nom.matches("[A-Z][a-z]*");
    }

    public static String getStringNN(Object o){

        if(o == null){
            return "";
        }

        return o.toString();
    }

}
