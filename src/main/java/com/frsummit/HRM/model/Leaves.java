package com.frsummit.HRM.model;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "leaves")
public class Leaves implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id", nullable = false)
    @NotEmpty(message = "*Set user id")
    private String userId;
    /*
    private String userName;
    private String userDepartment;
    private String userDesignation;
    */

    @Column(name = "apply_date", nullable = false)
    @UpdateTimestamp
    private Date leaveApplyDate;

    @Column(name = "apply_from", nullable = false)
    @UpdateTimestamp
    private Date leaveApplyFrom;

    @Column(name = "apply_to", nullable = false)
    @UpdateTimestamp
    private Date leaveApplyTo;

//    @Column(name = "total_leave_day")
//    private int totalDayOfLeave;

    @Column(name = "reason", nullable = false)
    private String leaveReason;

    @Column(name = "type", nullable = false)
    private String leaveType;

    @Column(name = "status", nullable = false)
    private String leaveStatus;

    @Column(name = "total_leave", nullable = false)
    private int totalLeave;

    @Column(name = "total_leave_personal", nullable = false)
    private int totalLeavePersonal;

    @Column(name = "total_leave_sick", nullable = false)
    private int totalLeaveSick;

    @Column(name = "total_leave_planned", nullable = false)
    private int totalLeavePlanned;

    @Column(name = "total_leave_vacation", nullable = false)
    private int totalLeaveVacation;

    @Column(name = "total_leave_maternity", nullable = false)
    private int totalLeaveMaternity;

    @Column(name = "total_leave_other", nullable = false)
    private int totalLeaveOther;

    @Column(name = "taken", nullable = false)
    private int totalLeaveTaken;

    @Column(name = "taken_personal", nullable = false)
    private int totalLeaveTakenPersonal;

    @Column(name = "taken_sick", nullable = false)
    private int totalLeaveTakenSick;

    @Column(name = "taken_planned", nullable = false)
    private int totalLeaveTakenPlanned;

    @Column(name = "taken_vacation", nullable = false)
    private int totalLeaveTakenVacation;

    @Column(name = "taken_maternity", nullable = false)
    private int totalLeaveTakenMaternity;

    @Column(name = "taken_other", nullable = false)
    private int totalLeaveTakenOther;

    @Column(name = "balance", nullable = false)
    private int leaveBalance;

    @Column(name = "balance_personal", nullable = false)
    private int leaveBalancePersonal;

    @Column(name = "balance_sick", nullable = false)
    private int leaveBalanceSick;

    @Column(name = "balance_planned", nullable = false)
    private int leaveBalancePlanned;

    @Column(name = "balance_vacation", nullable = false)
    private int leaveBalanceVacation;

    @Column(name = "balance_maternity", nullable = false)
    private int leaveBalanceMaternity;

    @Column(name = "balance_other", nullable = false)
    private int leaveBalanceOther;

    @Column(name = "apply_whom", nullable = false)
    private String applyWhom;

    @Column(name = "modify_to", nullable = false)
    private String modifyToWhom;

    @Column(name = "action_by", nullable = false)
    private String leaveActionBy;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "leave_user", joinColumns = @JoinColumn(name = "leave_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> user;

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

    public Date getLeaveApplyDate() {
        return leaveApplyDate;
    }

    public void setLeaveApplyDate(Date leaveApplyDate) {
        this.leaveApplyDate = leaveApplyDate;
    }

    public Date getLeaveApplyFrom() {
        return leaveApplyFrom;
    }

    public void setLeaveApplyFrom(Date leaveApplyFrom) {
        this.leaveApplyFrom = leaveApplyFrom;
    }

    public Date getLeaveApplyTo() {
        return leaveApplyTo;
    }

    public void setLeaveApplyTo(Date leaveApplyTo) {
        this.leaveApplyTo = leaveApplyTo;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public int getTotalLeave() {
        return totalLeave;
    }

    public int getTotalLeavePersonal() {
        return totalLeavePersonal;
    }

    public void setTotalLeavePersonal(int totalLeavePersonal) {
        this.totalLeavePersonal = totalLeavePersonal;
    }

    public int getTotalLeaveSick() {
        return totalLeaveSick;
    }

    public void setTotalLeaveSick(int totalLeaveSick) {
        this.totalLeaveSick = totalLeaveSick;
    }

    public int getTotalLeavePlanned() {
        return totalLeavePlanned;
    }

    public void setTotalLeavePlanned(int totalLeavePlanned) {
        this.totalLeavePlanned = totalLeavePlanned;
    }

    public int getTotalLeaveVacation() {
        return totalLeaveVacation;
    }

    public void setTotalLeaveVacation(int totalLeaveVacation) {
        this.totalLeaveVacation = totalLeaveVacation;
    }

    public int getTotalLeaveMaternity() {
        return totalLeaveMaternity;
    }

    public void setTotalLeaveMaternity(int totalLeaveMaternity) {
        this.totalLeaveMaternity = totalLeaveMaternity;
    }

    public int getTotalLeaveOther() {
        return totalLeaveOther;
    }

    public void setTotalLeaveOther(int totalLeaveOther) {
        this.totalLeaveOther = totalLeaveOther;
    }

    public int getTotalLeaveTakenPersonal() {
        return totalLeaveTakenPersonal;
    }

    public void setTotalLeaveTakenPersonal(int totalLeaveTakenPersonal) {
        this.totalLeaveTakenPersonal = totalLeaveTakenPersonal;
    }

    public int getTotalLeaveTakenSick() {
        return totalLeaveTakenSick;
    }

    public void setTotalLeaveTakenSick(int totalLeaveTakenSick) {
        this.totalLeaveTakenSick = totalLeaveTakenSick;
    }

    public int getTotalLeaveTakenPlanned() {
        return totalLeaveTakenPlanned;
    }

    public void setTotalLeaveTakenPlanned(int totalLeaveTakenPlanned) {
        this.totalLeaveTakenPlanned = totalLeaveTakenPlanned;
    }

    public int getTotalLeaveTakenVacation() {
        return totalLeaveTakenVacation;
    }

    public void setTotalLeaveTakenVacation(int totalLeaveTakenVacation) {
        this.totalLeaveTakenVacation = totalLeaveTakenVacation;
    }

    public int getTotalLeaveTakenMaternity() {
        return totalLeaveTakenMaternity;
    }

    public void setTotalLeaveTakenMaternity(int totalLeaveTakenMaternity) {
        this.totalLeaveTakenMaternity = totalLeaveTakenMaternity;
    }

    public int getTotalLeaveTakenOther() {
        return totalLeaveTakenOther;
    }

    public void setTotalLeaveTakenOther(int totalLeaveTakenOther) {
        this.totalLeaveTakenOther = totalLeaveTakenOther;
    }

    public int getLeaveBalancePersonal() {
        return leaveBalancePersonal;
    }

    public void setLeaveBalancePersonal(int leaveBalancePersonal) {
        this.leaveBalancePersonal = leaveBalancePersonal;
    }

    public int getLeaveBalanceSick() {
        return leaveBalanceSick;
    }

    public void setLeaveBalanceSick(int leaveBalanceSick) {
        this.leaveBalanceSick = leaveBalanceSick;
    }

    public int getLeaveBalancePlanned() {
        return leaveBalancePlanned;
    }

    public void setLeaveBalancePlanned(int leaveBalancePlanned) {
        this.leaveBalancePlanned = leaveBalancePlanned;
    }

    public int getLeaveBalanceVacation() {
        return leaveBalanceVacation;
    }

    public void setLeaveBalanceVacation(int leaveBalanceVacation) {
        this.leaveBalanceVacation = leaveBalanceVacation;
    }

    public int getLeaveBalanceMaternity() {
        return leaveBalanceMaternity;
    }

    public void setLeaveBalanceMaternity(int leaveBalanceMaternity) {
        this.leaveBalanceMaternity = leaveBalanceMaternity;
    }

    public int getLeaveBalanceOther() {
        return leaveBalanceOther;
    }

    public void setLeaveBalanceOther(int leaveBalanceOther) {
        this.leaveBalanceOther = leaveBalanceOther;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public void setTotalLeave(int totalLeave) {
        this.totalLeave = totalLeave;
    }

    public int getTotalLeaveTaken() {
        return totalLeaveTaken;
    }

    public void setTotalLeaveTaken(int totalLeaveTaken) {
        this.totalLeaveTaken = totalLeaveTaken;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public String getApplyWhom() {
        return applyWhom;
    }

    public void setApplyWhom(String applyWhom) {
        this.applyWhom = applyWhom;
    }

    public String getModifyToWhom() {
        return modifyToWhom;
    }

    public void setModifyToWhom(String modifyToWhom) {
        this.modifyToWhom = modifyToWhom;
    }

    public String getLeaveActionBy() {
        return leaveActionBy;
    }

    public void setLeaveActionBy(String leaveActionBy) {
        this.leaveActionBy = leaveActionBy;
    }
}