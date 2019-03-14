package com.revature.DAO;

import com.revature.models.Principal;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.service.ReimbService;
import com.revature.util.ConnectionFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ReimbursementDAO implements DAO<Reimbursement> {

	private static Logger log = Logger.getLogger(ReimbService.class);
    
	
	
	@Override
    public Reimbursement getById(int reimb_id) {
    		
        Reimbursement reimb = new Reimbursement();
        User user = new User();
        ArrayList<Reimbursement> reimbursements = new ArrayList<>();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

        	if(conn != null) {
        		
        		String sql = "SELECT * FROM chrisomar.ERS_Reimbursement WHERE reimb_id = ?";

                PreparedStatement prepState = conn.prepareStatement(sql);

                prepState.setInt(1, reimb_id);
                
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
        		
        	} else {
        		throw new SQLException("A connection could not be established");
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
            pstmt.setInt(4, reimb.getReimbAuthor());
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
        
    	try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "UPDATE chrisomar.ers_reimbursement SET reimb_status_id = ? WHERE reimb_id = ?";
		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, updatedObj.getReimbStatusID());
			pstmt.setInt(2,  updatedObj.getReimbID());
			

			
			int rowsUpdated = pstmt.executeUpdate();
			
			if(rowsUpdated != 0) {
				conn.commit();
				return updatedObj;
			}
		} catch (SQLIntegrityConstraintViolationException sicve) {
			System.out.println("[ERROR] - Username already taken");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Reimbursement> getAll() {

        
        ArrayList<Reimbursement> reimbursements = new ArrayList<>();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "SELECT * FROM chrisomar.ERS_Reimbursement";

            PreparedStatement prepState = conn.prepareStatement(sql);

            ResultSet rs = prepState.executeQuery();

            while(rs.next()) {
            	
            	Reimbursement reimb = new Reimbursement();

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
        
        System.out.println(reimbursements);
        return reimbursements;
    }
}
