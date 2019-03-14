package com.revature.util;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class RequestViewHelper {

    private static Logger log = Logger.getLogger(RequestViewHelper.class);

    private RequestViewHelper() {
        super();
    }

    public static String process(HttpServletRequest request) {

        switch(request.getRequestURI()) {

            case "/Project_1/login.view":
                log.info("Fetching login.html");
                return "partials/login.html";

            case "/Project_1/register.view":
                log.info("Fetching register.html");
                return "partials/register.html";

            case "/Project_1/dashboard.view":
                log.info("Fetching dashboard.html");
                return "partials/dashboard.html";
                
            case "/Project_1/managerdash.view":
            	log.info("Fetching managerdash.html");
            	return "partials/managerdash.html";
                
            case "/Project_1/submit_reimbursement.view":
            	log.info("Fetching submit_reimbursement.html");
            	return "partials/submit_reimbursement.html";
            	
            case "/Project_1/history.view":
            	log.info("Fetching history.html");
            	return "partials/history.html";
            	
            case "/Project_1/contact.view":
            	log.info("Fetching contact.html");
            	return "partials/contact.html";
            	
            case "/Project_1/pending.view":
            	log.info("Fetching pending.html");
            	return "partials/pending.html";
            	
            case "/Project_1/denials.view":
            	log.info("Fetching denials.html");
            	return "partials/denials.html";
            	
            case "/Project_1/approvals.view":
            	log.info("Fetching approvals.html");
            	return "partials/approvals.html";

            default:
                log.info("Invalid view requested");
                return null;
        }
    }
}
