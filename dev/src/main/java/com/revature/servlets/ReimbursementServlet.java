package com.revature.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.models.Principal;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.service.ReimbService;
import com.revature.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/reimb")
public class ReimbursementServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ReimbursementServlet.class);
	private static ReimbService reimbService = new ReimbService();
	private static UserService userService = new UserService();
			
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Principal principal = (Principal) req.getAttribute("principal");
		System.out.println(principal + " line 35 -- ReimbursementServlet");
		Reimbursement reimb = null;
		ObjectMapper mapper = new ObjectMapper();
		
		try {

			
			reimb = mapper.readValue(req.getInputStream(), Reimbursement.class);
			System.out.println(reimb + " ReimbServlett 38");
			System.out.println(principal + " ReimbServlett 39");
			//reimb.setReimbAuthor(principal.getUser_id());
			
		} catch (MismatchedInputException mie) {
			mie.printStackTrace();
			log.error(mie.getMessage());
			resp.setStatus(400);
			return;
		} catch (Exception e) {
			log.error(e.getMessage());
			resp.setStatus(500);
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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		Principal principal = (Principal) req.getAttribute("principal");
		
		System.out.println(principal + " line 78");
		
		String requestURI = req.getRequestURI();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			PrintWriter out = resp.getWriter();
			
			if(principal == null) {
				log.warn("No principal attribute found on request");
				resp.setStatus(401);
				return;
			}
			
			if(requestURI.equals("/project_1/users") || requestURI.equals("/project_1/users/")) {
				
				if (!principal.getRole_id().equalsIgnoreCase("ADMIN")) {
					log.warn("Unauthorized access attempt made from origin: " + req.getLocalAddr());
					resp.setStatus(401);
					return;
				}
				
				List<User> users = userService.getAllUsers();
				String usersJSON = mapper.writeValueAsString(users);
				resp.setStatus(200);
				out.write(usersJSON);
				
			} else if (requestURI.contains("users/")) {
				
				String[] fragments = requestURI.split("/");
				
				int userId = Integer.parseInt(fragments[3]);
					
				if (!principal.getRole_id().equalsIgnoreCase("2") && !principal.getRole_id().equalsIgnoreCase(Integer.toString(userId))) {
					log.warn("Unauthorized access attempt made from origin: " + req.getLocalAddr());
					resp.setStatus(401);
					return;
				}
					
				User user = userService.getById(userId);
				String userJSON = mapper.writeValueAsString(user);
				resp.setStatus(200);
				out.write(userJSON);
					
			} 
		} catch (NumberFormatException nfe) {
				log.error(nfe.getMessage());
				resp.setStatus(400);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			resp.setStatus(500);
		}
	}
}