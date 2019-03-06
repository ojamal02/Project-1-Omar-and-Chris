package com.revature.DAO;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO implements DAO<Reimbursement> {

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
                reimb.setReimbResolved(rs.getTimestamp("reimb_resolved"));
                reimb.setReimbDesc(rs.getString("reimb_description"));
                reimb.setReimbReceipt(rs.getBlob("reimb_receipt"));

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

    @Override
    public Reimbursement add(Reimbursement obj) {
        return null;
    }

    @Override
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
                reimb.setReimbResolved(rs.getTimestamp("reimb_resolved"));
                reimb.setReimbDesc(rs.getString("reimb_description"));
                reimb.setReimbReceipt(rs.getBlob("reimb_receipt"));

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
