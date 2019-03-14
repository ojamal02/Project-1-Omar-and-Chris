package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.models.Reimbursement;
import com.revature.service.ReimbService;
import com.revature.service.UserService;

@WebServlet("/approval")
public class ApprovalServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ApprovalServlet.class);
	private static ReimbService reimbService = new ReimbService();
	private static UserService userService = new UserService();
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
		Reimbursement reimb = null;
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			
			reimb = mapper.readValue(req.getInputStream(), Reimbursement.class);
			
		} catch (MismatchedInputException mie) {
			mie.printStackTrace();
			log.error(mie.getMessage());
			resp.setStatus(400);
			return;
		}
		
		reimb = reimbService.addReimb(reimb);
		
		try {
			String reimbJson = mapper.writeValueAsString(reimb);
			PrintWriter out = resp.getWriter();
			out.write(reimbJson);
		} catch (Exception e) {
			log.error(e.getMessage());
			resp.setStatus(500);
		}
    	
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	response.setContentType("application/json");
    	PrintWriter out = response.getWriter();
    	ObjectMapper mapper = new ObjectMapper();

    	
    	List<Reimbursement> allReimb = reimbService.getAllReimb();
    	
    	if(allReimb.isEmpty()) {
    		
    		log.info("Reimbursement data is empty");
    		response.setStatus(400);
    		return;
    	}
    	
    	List<Reimbursement> pending = new ArrayList<Reimbursement>();
    	
    	
    	for (Reimbursement reimbursement : allReimb) {
    		if (reimbursement.getReimbStatusID() == 1)
    			pending.add(reimbursement);
    		System.out.println(pending);
    	}
    	
    	if (pending.isEmpty()) {
    		
    		log.info("Reimbursements were empty");
    		
    		response.setStatus(400);
    		return;
    	}
    	
    	response.setStatus(200);
    	String resp = mapper.writeValueAsString(pending);
    	response.setContentType("application/json");
    	out.write(resp);
    	
    }
}
