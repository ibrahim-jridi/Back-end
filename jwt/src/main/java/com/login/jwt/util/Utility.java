package com.login.jwt.util;

import javax.servlet.http.HttpServletRequest;

public class Utility {
	public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

}
/* The getSiteURL() method returns the application’s URL which can be used in production, 
 * so the user will be able to click the link in the email.*/
