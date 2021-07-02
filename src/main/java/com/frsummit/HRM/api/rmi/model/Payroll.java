package com.frsummit.HRM.api.rmi.model;

import java.io.Serializable;

public class Payroll implements Serializable {
    private int id;
    private String userId;
    private double basic;
    private double stipen;
    private double houseRent;
    private double conveyance;
    private double medical;
    private double advanceOrLoan;
    private double interest;
    private double otherAll;
    private double educationalAll;
    private double bonus;
    private double incentiveOrCommission;
    private double leaveEncashment;
    private double perquisite;
    private double reimbursement;
    private double special;
    private double overtime;
    private double arrears;
    private double childFund;
    private double tds;
    private double interestDED;
    private double pf;
    private double professionalTaxDED;
    private double advanceOrLoanDED;
    private double otherDED;
    private double leaveDED;
    private double securityDepositeDED;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getBasic() {
        return basic;
    }

    public void setBasic(double basic) {
        this.basic = basic;
    }

    public double getStipen() {
        return stipen;
    }

    public void setStipen(double stipen) {
        this.stipen = stipen;
    }

    public double getHouseRent() {
        return houseRent;
    }

    public void setHouseRent(double houseRent) {
        this.houseRent = houseRent;
    }

    public double getConveyance() {
        return conveyance;
    }

    public void setConveyance(double conveyance) {
        this.conveyance = conveyance;
    }

    public double getMedical() {
        return medical;
    }

    public void setMedical(double medical) {
        this.medical = medical;
    }

    public double getAdvanceOrLoan() {
        return advanceOrLoan;
    }

    public void setAdvanceOrLoan(double advanceOrLoan) {
        this.advanceOrLoan = advanceOrLoan;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getOtherAll() {
        return otherAll;
    }

    public void setOtherAll(double otherAll) {
        this.otherAll = otherAll;
    }

    public double getEducationalAll() {
        return educationalAll;
    }

    public void setEducationalAll(double educationalAll) {
        this.educationalAll = educationalAll;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getIncentiveOrCommission() {
        return incentiveOrCommission;
    }

    public void setIncentiveOrCommission(double incentiveOrCommission) {
        this.incentiveOrCommission = incentiveOrCommission;
    }

    public double getLeaveEncashment() {
        return leaveEncashment;
    }

    public void setLeaveEncashment(double leaveEncashment) {
        this.leaveEncashment = leaveEncashment;
    }

    public double getPerquisite() {
        return perquisite;
    }

    public void setPerquisite(double perquisite) {
        this.perquisite = perquisite;
    }

    public double getReimbursement() {
        return reimbursement;
    }

    public void setReimbursement(double reimbursement) {
        this.reimbursement = reimbursement;
    }

    public double getSpecial() {
        return special;
    }

    public void setSpecial(double special) {
        this.special = special;
    }

    public double getOvertime() {
        return overtime;
    }

    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }

    public double getArrears() {
        return arrears;
    }

    public void setArrears(double arrears) {
        this.arrears = arrears;
    }

    public double getChildFund() {
        return childFund;
    }

    public void setChildFund(double childFund) {
        this.childFund = childFund;
    }

    public double getTds() {
        return tds;
    }

    public void setTds(double tds) {
        this.tds = tds;
    }

    public double getInterestDED() {
        return interestDED;
    }

    public void setInterestDED(double interestDED) {
        this.interestDED = interestDED;
    }

    public double getPf() {
        return pf;
    }

    public void setPf(double pf) {
        this.pf = pf;
    }

    public double getProfessionalTaxDED() {
        return professionalTaxDED;
    }

    public void setProfessionalTaxDED(double professionalTaxDED) {
        this.professionalTaxDED = professionalTaxDED;
    }

    public double getAdvanceOrLoanDED() {
        return advanceOrLoanDED;
    }

    public void setAdvanceOrLoanDED(double advanceOrLoanDED) {
        this.advanceOrLoanDED = advanceOrLoanDED;
    }

    public double getOtherDED() {
        return otherDED;
    }

    public void setOtherDED(double otherDED) {
        this.otherDED = otherDED;
    }

    public double getLeaveDED() {
        return leaveDED;
    }

    public void setLeaveDED(double leaveDED) {
        this.leaveDED = leaveDED;
    }

    public double getSecurityDepositeDED() {
        return securityDepositeDED;
    }

    public void setSecurityDepositeDED(double securityDepositeDED) {
        this.securityDepositeDED = securityDepositeDED;
    }

	public com.frsummit.HRM.model.Payroll toLocalModel() {
		com.frsummit.HRM.model.Payroll payroll = new com.frsummit.HRM.model.Payroll();
		payroll.setAdvanceOrLoan(this.getAdvanceOrLoan());
		payroll.setAdvanceOrLoanDED(this.getAdvanceOrLoanDED());
		payroll.setArrears(this.getArrears());
		payroll.setBasic(this.getBasic());
		payroll.setBonus(this.getBonus());
		payroll.setChildFund(this.getChildFund());
		payroll.setConveyance(this.getConveyance());
		payroll.setEducationalAll(this.getEducationalAll());
		payroll.setHouseRent(this.getHouseRent());
		payroll.setId(this.getId());
		payroll.setIncentiveOrCommission(this.getIncentiveOrCommission());
		payroll.setInterest(this.getInterest());
		payroll.setInterestDED(this.getInterestDED());
		payroll.setLeaveDED(this.getLeaveDED());
		payroll.setLeaveEncashment(this.leaveEncashment);
		payroll.setMedical(this.getMedical());
		payroll.setOtherAll(this.getOtherAll());
		payroll.setOtherDED(this.getOtherDED());
		payroll.setOvertime(this.getOvertime());
		payroll.setPerquisite(this.getPerquisite());
		payroll.setPf(this.getPf());
		payroll.setProfessionalTaxDED(this.getProfessionalTaxDED());
		payroll.setReimbursement(this.getReimbursement());
		payroll.setSecurityDepositeDED(this.getSecurityDepositeDED());
		payroll.setSpecial(this.getSpecial());
		payroll.setStipen(this.getStipen());
		payroll.setTds(this.getTds());
		payroll.setUserId(this.getUserId());
		
		return payroll;
	}
}
