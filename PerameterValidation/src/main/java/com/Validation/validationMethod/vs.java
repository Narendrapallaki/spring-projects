package com.Validation.validationMethod;

import java.util.regex.Pattern;

public class vs {
	
	
	public static boolean isMobile(Long num)
	{
		
		boolean b = Pattern.matches("^\\d{10}$", String.valueOf(num));
		
		return b;
		
	}
	
	public static boolean isPassword(String pass)
	{
		
		String rex= "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
		
		boolean b = Pattern.matches(rex, pass);
		return b;
		
	}

}
