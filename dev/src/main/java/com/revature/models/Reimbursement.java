package com.revature.models;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;

public class Reimbursement {

    private int reimbID;
    private double reimbAmt;
    private Timestamp reimbSubmitted;
    private String reimbDesc;
    // Foreign keys
    private int reimbAuthor;
    private int reimbResolver;
    private int reimbStatusID;
    private int reimbTypeID;

    public Reimbursement() {
    }

    public Reimbursement(int reimbID, double reimbAmt, Timestamp reimbSubmitted, Timestamp reimbResolved, String reimbDesc, Blob reimbReceipt, int reimbAuthor, int reimbResolver, int reimbStatusID, int reimbTypeID) {
        this.reimbID = reimbID;
        this.reimbAmt = reimbAmt;
        this.reimbSubmitted = reimbSubmitted;

        this.reimbDesc = reimbDesc;

        this.reimbAuthor = reimbAuthor;
        this.reimbResolver = reimbResolver;
        this.reimbStatusID = reimbStatusID;
        this.reimbTypeID = reimbTypeID;
    }
    

    public int getReimbID() {
        return reimbID;
    }

    public void setReimbID(int reimbID) {
        this.reimbID = reimbID;
    }

    public double getReimbAmt() {
        return reimbAmt;
    }

    public void setReimbAmt(double reimbAmt) {
        this.reimbAmt = reimbAmt;
    }

    public Timestamp getReimbSubmitted() {
        return reimbSubmitted;
    }

    public void setReimbSubmitted(Timestamp reimbSubmitted) {
        this.reimbSubmitted = reimbSubmitted;
    }

    

    public String getReimbDesc() {
        return reimbDesc;
    }

    public void setReimbDesc(String reimbDesc) {
        this.reimbDesc = reimbDesc;
    }

   

    public int getReimbAuthor() {
        return reimbAuthor;
    }

    public void setReimbAuthor(int reimbAuthor) {
        this.reimbAuthor = reimbAuthor;
    }

    public int getReimbResolver() {
        return reimbResolver;
    }

    public void setReimbResolver(int reimbResolver) {
        this.reimbResolver = reimbResolver;
    }

    public int getReimbStatusID() {
        return reimbStatusID;
    }

    public void setReimbStatusID(int reimbStatusID) {
        this.reimbStatusID = reimbStatusID;
    }

    public int getReimbTypeID() {
        return reimbTypeID;
    }

    public void setReimbTypeID(int reimbTypeID) {
        this.reimbTypeID = reimbTypeID;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbID=" + reimbID +
                ", reimbAmt=" + reimbAmt +
                ", reimbSubmitted=" + reimbSubmitted +

                ", reimbDesc='" + reimbDesc + '\'' +

                ", reimbAuthor=" + reimbAuthor +
                ", reimbResolver=" + reimbResolver +
                ", reimbStatusID=" + reimbStatusID +
                ", reimbTypeID=" + reimbTypeID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return reimbID == that.reimbID &&
                Double.compare(that.reimbAmt, reimbAmt) == 0 &&
                reimbAuthor == that.reimbAuthor &&
                reimbResolver == that.reimbResolver &&
                reimbStatusID == that.reimbStatusID &&
                reimbTypeID == that.reimbTypeID &&
                reimbSubmitted.equals(that.reimbSubmitted) &&
      
                reimbDesc.equals(that.reimbDesc);
            
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbID, reimbAmt, reimbSubmitted, reimbDesc, reimbAuthor, reimbResolver, reimbStatusID, reimbTypeID);
    }
}
