package utils;

import java.util.regex.Pattern;

public class StrUtils {
    public static boolean equals(String str, String pattern){
        return Pattern.matches(pattern,str);
    }
}
