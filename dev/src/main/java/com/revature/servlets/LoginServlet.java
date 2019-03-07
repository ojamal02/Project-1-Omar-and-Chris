package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.models.User;
import com.revature.service.UserService;
import com.revature.util.JWTConfig;
import com.revature.util.JWTGenerator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(LoginServlet.class);
    private static UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        String[] credentials = null;

        try {
            credentials = mapper.readValue(req.getInputStream(), String[].class);
        } catch (MismatchedInputException mie) {
            log.error(mie.getMessage());
            resp.setStatus(400);
            return;
        } catch (Exception e) {
            log.error(e.getMessage());
            resp.setStatus(500);
            return;
        }

        if(credentials != null && credentials.length != 2) {
            log.warn("Invalid request, unexpected number of credential values");
            resp.setStatus(400);
            return;
        }

        User user = userService.getUserByCredentials(credentials[0], credentials[1]);

        if(user == null) {
            resp.setStatus(401);
            return;
        }

        resp.setStatus(200);
        resp.addHeader(JWTConfig.HEADER, JWTConfig.PREFIX + JWTGenerator.createJWT(user));
    }
}
