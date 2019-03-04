package com.revature.models;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;

public class Reimbursement {

    private int reimbID;
    private double reimbAmt;
    private Timestamp reimbSubmitted;
    private Timestamp reimbResolved;
    private String reimbDesc;
    private Blob reimbReceipt;

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
        this.reimbResolved = reimbResolved;
        this.reimbDesc = reimbDesc;
        this.reimbReceipt = reimbReceipt;
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

    public Timestamp getReimbResolved() {
        return reimbResolved;
    }

    public void setReimbResolved(Timestamp reimbResolved) {
        this.reimbResolved = reimbResolved;
    }

    public String getReimbDesc() {
        return reimbDesc;
    }

    public void setReimbDesc(String reimbDesc) {
        this.reimbDesc = reimbDesc;
    }

    public Blob getReimbReceipt() {
        return reimbReceipt;
    }

    public void setReimbReceipt(Blob reimbReceipt) {
        this.reimbReceipt = reimbReceipt;
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
                ", reimbResolved=" + reimbResolved +
                ", reimbDesc='" + reimbDesc + '\'' +
                ", reimbReceipt=" + reimbReceipt +
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
                reimbResolved.equals(that.reimbResolved) &&
                reimbDesc.equals(that.reimbDesc) &&
                Objects.equals(reimbReceipt, that.reimbReceipt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbID, reimbAmt, reimbSubmitted, reimbResolved, reimbDesc, reimbReceipt, reimbAuthor, reimbResolver, reimbStatusID, reimbTypeID);
    }
}
