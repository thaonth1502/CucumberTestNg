package thaonth7.fpt.com.helpers;

import java.io.File;
import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class SystemHelper {

    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");


    public static String getCurrentDir(){
        String current = System.getProperty("user.dir") + File.separator;
        return current;
    }

    public static String makeSlug(String input){
        if (input == null){
            throw new IllegalArgumentException();
        }
        String noWhiteSpace = WHITESPACE.matcher(input).replaceAll("_");
        String normalized = Normalizer.normalize(noWhiteSpace, Normalizer.Form.NFD);
        String slug = NON_LATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
}
