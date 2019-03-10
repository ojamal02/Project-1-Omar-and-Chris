package com.revature.DAO;

import com.revature.models.Principal;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.service.ReimbService;
import com.revature.util.ConnectionFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ReimbursementDAO implements DAO<Reimbursement> {

	Principal principal = new Principal();
	private static Logger log = Logger.getLogger(ReimbService.class);
    @Override
    public Reimbursement getById(int id) {
    	
    	
    	
        Reimbursement reimb = new Reimbursement();
        ArrayList<Reimbursement> reimbursements = new ArrayList<>();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "SELECT * FROM ERS_Reimbursement WHERE reimb_id = ?";

            PreparedStatement prepState = conn.prepareStatement(sql);

            ResultSet rs = prepState.executeQuery();

            while(rs.next()) {

                reimb.setReimbID(rs.getInt("reimb_id"));
                reimb.setReimbAmt(rs.getDouble("reimb_amount"));
                reimb.setReimbSubmitted(rs.getTimestamp("reimb_submitted"));
                reimb.setReimbDesc(rs.getString("reimb_description"));
                reimb.setReimbAuthor(rs.getInt("reimb_author"));
                reimb.setReimbResolver(rs.getInt("reimb_resolver"));
                reimb.setReimbStatusID(rs.getInt("reimb_status_id"));
                reimb.setReimbTypeID(rs.getInt("reimb_type_id"));

                reimbursements.add(reimb);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return reimb;
    }
    
    private static java.sql.Timestamp getCurrentTimeStamp() {

    	java.util.Date today = new java.util.Date();
    	return new java.sql.Timestamp(today.getTime());

    }
    
//    public Reimbursement getByAuthor(int author_id) {
//    	
//    	try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
//    		
//    		String sql = "SELECT * FROM chrisomar.ers_reimbursement JOIN chrisomar.ers_users ON ers_reimbursement.reimb_author = ers_users.ers_users_id WHERE reimb_author = ?";
//    	
//    		 PreparedStatement pstmt = conn.prepareStatement(sql);
//    		 
//    		 pstmt.setInt(1, author_id);
//
//             ResultSet rs = pstmt.executeQuery();
//             
//             while (rs.next()) {
//            	 
//             }
//    		
//    	} catch (SQLException sqle) {
//    		
//    	}
//    }

    @Override
    public Reimbursement add(Reimbursement reimb) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            User user = new User();
           
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO chrisomar.ers_reimbursement VALUES (2, ?, ?, ?, ?, null, 1, ?)", new String[] {"reimb_id"});
            
            
            //pstmt.setInt(1, reimb.getReimbID());
            pstmt.setDouble(1, reimb.getReimbAmt());
            pstmt.setTimestamp(2, getCurrentTimeStamp());
            pstmt.setString(3, reimb.getReimbDesc());
            System.out.println(principal);
            pstmt.setInt(4, user.getUser_id());
            pstmt.setInt(5, reimb.getReimbTypeID());
           

            if(pstmt.executeUpdate() != 0) {

                // Retrieve the generated primary key for the newly added user
                ResultSet rs = pstmt.getGeneratedKeys();


			while(rs.next()) {
				reimb.setReimbID(rs.getInt(1));
			}

                conn.commit();

            }

        } catch (SQLIntegrityConstraintViolationException sicve) {
            log.error(sicve.getMessage());
            log.warn("Username already taken.");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        //if(newUser.getId() == 0) return null;

        return reimb;
    }

    //@Override
    public Reimbursement update(Reimbursement updatedObj) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Reimbursement> getAll() {

        Reimbursement reimb = new Reimbursement();
        ArrayList<Reimbursement> reimbursements = new ArrayList<>();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "SELECT * FROM ERS_Reimbursement";

            PreparedStatement prepState = conn.prepareStatement(sql);

            ResultSet rs = prepState.executeQuery();

            while(rs.next()) {

                reimb.setReimbID(rs.getInt("reimb_id"));
                reimb.setReimbAmt(rs.getDouble("reimb_amount"));
                reimb.setReimbSubmitted(rs.getTimestamp("reimb_submitted"));
                reimb.setReimbDesc(rs.getString("reimb_description"));
                reimb.setReimbAuthor(rs.getInt("reimb_author"));
                reimb.setReimbResolver(rs.getInt("reimb_resolver"));
                reimb.setReimbStatusID(rs.getInt("reimb_status_id"));
                reimb.setReimbTypeID(rs.getInt("reimb_type_id"));

                reimbursements.add(reimb);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return reimbursements;
    }
}
