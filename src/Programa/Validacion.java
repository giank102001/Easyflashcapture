package Programa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacion {
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static boolean validateEmail(String email) {
		try{
		    // Compiles the given regular expression into a pattern.
		    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		    // Match the given input against this pattern
		    Matcher matcher = pattern.matcher(email);
		    return matcher.matches();
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
 }
}
