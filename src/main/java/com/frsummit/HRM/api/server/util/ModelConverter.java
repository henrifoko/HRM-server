package com.frsummit.HRM.api.server.util;

import java.util.HashSet;
import java.util.Set;

import com.frsummit.HRM.api.server.entity.Attendance;
import com.frsummit.HRM.api.server.entity.EmergencyContact;
import com.frsummit.HRM.api.server.entity.HRRecord;
import com.frsummit.HRM.api.server.entity.Leaves;
import com.frsummit.HRM.api.server.entity.Links;
import com.frsummit.HRM.api.server.entity.Message;
import com.frsummit.HRM.api.server.entity.Payroll;
import com.frsummit.HRM.api.server.entity.Role;
import com.frsummit.HRM.api.server.entity.TestImage;
import com.frsummit.HRM.api.server.entity.User;

public class ModelConverter {

	public static com.frsummit.HRM.model.User getServerVersion(User apiUser) {
		com.frsummit.HRM.model.User user = new com.frsummit.HRM.model.User();
		user.setActive(apiUser.getActive());
		user.setBloodGroup(apiUser.getBloodGroup());
		user.setDepartment(apiUser.getDepartment());
		user.setDesignation(apiUser.getDesignation());
		user.setDob(apiUser.getDob());
		user.setEmail(apiUser.getEmail());
		user.setFatherName(apiUser.getFatherName());
		user.setFirstName(apiUser.getFirstName());
		user.setId(apiUser.getId());
		user.setImageName(apiUser.getImageName());
		user.setIncomeTexNo(apiUser.getIncomeTexNo());
		user.setLastName(apiUser.getLastName());
		user.setMiddleName(apiUser.getMiddleName());
		user.setMotherName(apiUser.getMotherName());
		user.setMyRole(apiUser.getMyRole());
		user.setNationality(apiUser.getNationality());
		user.setNid(apiUser.getNid());
		user.setPassportNumber(apiUser.getPassportNumber());
		user.setPassword(apiUser.getPassword());

		com.frsummit.HRM.model.Address address = new com.frsummit.HRM.model.Address();
		com.frsummit.HRM.api.server.entity.Address permanentAddress = apiUser.getPermanentAddress();
		if (permanentAddress != null) {
			address.setCity(permanentAddress.getCity());
			address.setCountry(permanentAddress.getCountry());
			address.setDistrict(permanentAddress.getDistrict());
			address.setHouse(permanentAddress.getHouse());
			address.setPostOffice(permanentAddress.getPostOffice());
			address.setStreet(permanentAddress.getStreet());
		}

		user.setPermanentAddress(address);
		user.setPhone(apiUser.getPhone());

		address = new com.frsummit.HRM.model.Address();
		com.frsummit.HRM.api.server.entity.Address presentAddress = apiUser.getPermanentAddress();
		if (presentAddress != null) {
			address.setCity(presentAddress.getCity());
			address.setCountry(presentAddress.getCountry());
			address.setDistrict(presentAddress.getDistrict());
			address.setHouse(presentAddress.getHouse());
			address.setPostOffice(presentAddress.getPostOffice());
			address.setStreet(presentAddress.getStreet());
		}

		user.setPresentAddress(address);

		return user;
	}

	public static User getApiVersion(com.frsummit.HRM.model.User serverUser) {
		User user = new com.frsummit.HRM.api.server.entity.User();
		user.setActive(serverUser.getActive());
		user.setBloodGroup(serverUser.getBloodGroup());
		user.setDepartment(serverUser.getDepartment());
		user.setDesignation(serverUser.getDesignation());
		user.setDob(serverUser.getDob());
		user.setEmail(serverUser.getEmail());
		user.setFatherName(serverUser.getFatherName());
		user.setFirstName(serverUser.getFirstName());
		user.setId(serverUser.getId());
		user.setImageName(serverUser.getImageName());
		user.setIncomeTexNo(serverUser.getIncomeTexNo());
		user.setLastName(serverUser.getLastName());
		user.setMiddleName(serverUser.getMiddleName());
		user.setMotherName(serverUser.getMotherName());
		user.setMyRole(serverUser.getMyRole());
		user.setNationality(serverUser.getNationality());
		user.setNid(serverUser.getNid());
		user.setPassportNumber(serverUser.getPassportNumber());
		user.setPassword(serverUser.getPassword());

		com.frsummit.HRM.api.server.entity.Address address = new com.frsummit.HRM.api.server.entity.Address();
		com.frsummit.HRM.model.Address permanentAddress = serverUser.getPermanentAddress();
		if (permanentAddress != null) {
			address.setCity(permanentAddress.getCity());
			address.setCountry(permanentAddress.getCountry());
			address.setDistrict(permanentAddress.getDistrict());
			address.setHouse(permanentAddress.getHouse());
			address.setPostOffice(permanentAddress.getPostOffice());
			address.setStreet(permanentAddress.getStreet());
		}

		user.setPermanentAddress(address);
		user.setPhone(serverUser.getPhone());

		address = new com.frsummit.HRM.api.server.entity.Address();
		com.frsummit.HRM.model.Address presentAddress = serverUser.getPermanentAddress();
		if (presentAddress != null) {
			address.setCity(presentAddress.getCity());
			address.setCountry(presentAddress.getCountry());
			address.setDistrict(presentAddress.getDistrict());
			address.setHouse(presentAddress.getHouse());
			address.setPostOffice(presentAddress.getPostOffice());
			address.setStreet(presentAddress.getStreet());
		}

		user.setPresentAddress(address);

		return user;
	}

	public static com.frsummit.HRM.model.TestImage getServerVersion(TestImage ti) {
		com.frsummit.HRM.model.TestImage tiLocal = new com.frsummit.HRM.model.TestImage();
		tiLocal.setData(ti.getData());
		tiLocal.setFileName(ti.getFileName());
		tiLocal.setId(ti.getId());

		return tiLocal;
	}

	public static TestImage getServerVersion(com.frsummit.HRM.model.TestImage ti) {
		TestImage tiLocal = new TestImage();
		tiLocal.setData(ti.getData());
		tiLocal.setFileName(ti.getFileName());
		tiLocal.setId(ti.getId());

		return tiLocal;
	}

	public static com.frsummit.HRM.model.Role getServerVersion(Role apiRole) {
		com.frsummit.HRM.model.Role role = new com.frsummit.HRM.model.Role();
		role.setId(apiRole.getId());
		role.setRole(apiRole.getRole());
		role.setRoleChain(apiRole.getRoleChain());

		return role;
	}

	public static Role getServerVersion(com.frsummit.HRM.model.Role serverRole) {
		Role role = new Role();
		role.setId(serverRole.getId());
		role.setRole(serverRole.getRole());
		role.setRoleChain(serverRole.getRoleChain());

		return role;
	}

	public static com.frsummit.HRM.model.Payroll getServerVersion(Payroll apiPayroll) {
		com.frsummit.HRM.model.Payroll payroll = new com.frsummit.HRM.model.Payroll();
		payroll.setAdvanceOrLoan(apiPayroll.getAdvanceOrLoan());
		payroll.setAdvanceOrLoanDED(apiPayroll.getAdvanceOrLoanDED());
		payroll.setArrears(apiPayroll.getArrears());
		payroll.setBasic(apiPayroll.getBasic());
		payroll.setBonus(apiPayroll.getBonus());
		payroll.setChildFund(apiPayroll.getChildFund());
		payroll.setConveyance(apiPayroll.getConveyance());
		payroll.setEducationalAll(apiPayroll.getEducationalAll());
		payroll.setHouseRent(apiPayroll.getHouseRent());
		payroll.setId(apiPayroll.getId());
		payroll.setIncentiveOrCommission(apiPayroll.getIncentiveOrCommission());
		payroll.setInterest(apiPayroll.getInterest());
		payroll.setInterestDED(apiPayroll.getInterestDED());
		payroll.setLeaveDED(apiPayroll.getLeaveDED());
		payroll.setLeaveEncashment(apiPayroll.getLeaveEncashment());
		payroll.setMedical(apiPayroll.getMedical());
		payroll.setOtherAll(apiPayroll.getOtherAll());
		payroll.setOtherDED(apiPayroll.getOtherDED());
		payroll.setOvertime(apiPayroll.getOvertime());
		payroll.setPerquisite(apiPayroll.getPerquisite());
		payroll.setPf(apiPayroll.getPf());
		payroll.setProfessionalTaxDED(apiPayroll.getProfessionalTaxDED());
		payroll.setReimbursement(apiPayroll.getReimbursement());
		payroll.setSecurityDepositeDED(apiPayroll.getSecurityDepositeDED());
		payroll.setSpecial(apiPayroll.getSpecial());
		payroll.setStipen(apiPayroll.getStipen());
		payroll.setTds(apiPayroll.getTds());
		payroll.setUserId(apiPayroll.getUserId());

		return payroll;
	}

	public static Payroll getServerVersion(com.frsummit.HRM.model.Payroll serverPayroll) {
		Payroll payroll = new Payroll();
		payroll.setAdvanceOrLoan(serverPayroll.getAdvanceOrLoan());
		payroll.setAdvanceOrLoanDED(serverPayroll.getAdvanceOrLoanDED());
		payroll.setArrears(serverPayroll.getArrears());
		payroll.setBasic(serverPayroll.getBasic());
		payroll.setBonus(serverPayroll.getBonus());
		payroll.setChildFund(serverPayroll.getChildFund());
		payroll.setConveyance(serverPayroll.getConveyance());
		payroll.setEducationalAll(serverPayroll.getEducationalAll());
		payroll.setHouseRent(serverPayroll.getHouseRent());
		payroll.setId(serverPayroll.getId());
		payroll.setIncentiveOrCommission(serverPayroll.getIncentiveOrCommission());
		payroll.setInterest(serverPayroll.getInterest());
		payroll.setInterestDED(serverPayroll.getInterestDED());
		payroll.setLeaveDED(serverPayroll.getLeaveDED());
		payroll.setLeaveEncashment(serverPayroll.getLeaveEncashment());
		payroll.setMedical(serverPayroll.getMedical());
		payroll.setOtherAll(serverPayroll.getOtherAll());
		payroll.setOtherDED(serverPayroll.getOtherDED());
		payroll.setOvertime(serverPayroll.getOvertime());
		payroll.setPerquisite(serverPayroll.getPerquisite());
		payroll.setPf(serverPayroll.getPf());
		payroll.setProfessionalTaxDED(serverPayroll.getProfessionalTaxDED());
		payroll.setReimbursement(serverPayroll.getReimbursement());
		payroll.setSecurityDepositeDED(serverPayroll.getSecurityDepositeDED());
		payroll.setSpecial(serverPayroll.getSpecial());
		payroll.setStipen(serverPayroll.getStipen());
		payroll.setTds(serverPayroll.getTds());
		payroll.setUserId(serverPayroll.getUserId());

		return payroll;
	}

	public static com.frsummit.HRM.model.Message getServerVersion(Message apiMessage) {
		com.frsummit.HRM.model.Message message = new com.frsummit.HRM.model.Message();
		message.setLeaveId(apiMessage.getLeaveId());
		message.setMessage(apiMessage.getMessage());
		message.setMessageCheck(apiMessage.getMessageCheck());
		message.setMessageId(apiMessage.getMessageId());
		message.setMessageUserId(apiMessage.getMessageUserId());

		return message;
	}

	public static Message getApiVersion(com.frsummit.HRM.model.Message serverMessage) {
		Message message = new Message();
		message.setLeaveId(serverMessage.getLeaveId());
		message.setMessage(serverMessage.getMessage());
		message.setMessageCheck(serverMessage.getMessageCheck());
		message.setMessageId(serverMessage.getMessageId());
		message.setMessageUserId(serverMessage.getMessageUserId());

		return message;
	}

	public static com.frsummit.HRM.model.Links getServerVersion(Links apiLinks) {
		com.frsummit.HRM.model.Links links = new com.frsummit.HRM.model.Links();
		links.setId(apiLinks.getId());
		links.setLinkName(apiLinks.getLinkName());
		links.setUrl(apiLinks.getUrl());

		return links;
	}
	
	public static Links getApiVersion(com.frsummit.HRM.model.Links serverLinks) {
		Links links = new Links();
		links.setId(serverLinks.getId());
		links.setLinkName(serverLinks.getLinkName());
		links.setUrl(serverLinks.getUrl());

		return links;
	}

	public static com.frsummit.HRM.model.Leaves getServerVersion(Leaves apiLeaves) {
		com.frsummit.HRM.model.Leaves leaves = new com.frsummit.HRM.model.Leaves();
		leaves.setApplyWhom(apiLeaves.getApplyWhom());
		leaves.setCancellationLeaveStatus(apiLeaves.getCancellationLeaveStatus());
		leaves.setId(apiLeaves.getId());
		leaves.setLeaveActionBy(apiLeaves.getLeaveActionBy());
		leaves.setLeaveApplyDate(apiLeaves.getLeaveApplyDate());
		leaves.setLeaveApplyFrom(apiLeaves.getLeaveApplyFrom());
		leaves.setLeaveApplyTo(apiLeaves.getLeaveApplyFrom());
		leaves.setLeaveDescription(apiLeaves.getLeaveDescription());
		leaves.setLeaveReason(apiLeaves.getLeaveReason());
		leaves.setLeaveStatus(apiLeaves.getLeaveStatus());
		leaves.setLeaveType(apiLeaves.getLeaveType());
		leaves.setModifyToWhom(apiLeaves.getModifyToWhom());
		leaves.setTotalDayOfLeave(apiLeaves.getTotalDayOfLeave());
		leaves.setUserId(apiLeaves.getUserId());

		Set<User> originSet = apiLeaves.getUserLeaves();
		Set<com.frsummit.HRM.model.User> targetSet = new HashSet<com.frsummit.HRM.model.User>();
		for (User u : originSet) {
			targetSet.add(ModelConverter.getServerVersion(u));
		}
		leaves.setUserLeaves(targetSet);

		return leaves;
	}

	public static Leaves getApiVersion(com.frsummit.HRM.model.Leaves serverLeaves) {
		Leaves leaves = new com.frsummit.HRM.api.server.entity.Leaves();
		leaves.setApplyWhom(serverLeaves.getApplyWhom());
		leaves.setCancellationLeaveStatus(serverLeaves.getCancellationLeaveStatus());
		leaves.setId(serverLeaves.getId());
		leaves.setLeaveActionBy(serverLeaves.getLeaveActionBy());
		leaves.setLeaveApplyDate(serverLeaves.getLeaveApplyDate());
		leaves.setLeaveApplyFrom(serverLeaves.getLeaveApplyFrom());
		leaves.setLeaveApplyTo(serverLeaves.getLeaveApplyFrom());
		leaves.setLeaveDescription(serverLeaves.getLeaveDescription());
		leaves.setLeaveReason(serverLeaves.getLeaveReason());
		leaves.setLeaveStatus(serverLeaves.getLeaveStatus());
		leaves.setLeaveType(serverLeaves.getLeaveType());
		leaves.setModifyToWhom(serverLeaves.getModifyToWhom());
		leaves.setTotalDayOfLeave(serverLeaves.getTotalDayOfLeave());
		leaves.setUserId(serverLeaves.getUserId());

		Set<com.frsummit.HRM.model.User> originSet = serverLeaves.getUserLeaves();
		Set<User> targetSet = new HashSet<User>();
		for (com.frsummit.HRM.model.User u : originSet) {
			targetSet.add(ModelConverter.getApiVersion(u));
		}
		leaves.setUserLeaves(targetSet);

		return leaves;
	}

	public static com.frsummit.HRM.model.HRRecord getServerVersion(HRRecord apiHRRecord) {
		com.frsummit.HRM.model.HRRecord hrRecord = new com.frsummit.HRM.model.HRRecord();
		Set<User> originSet = apiHRRecord.getHrRecordUser();
		Set<com.frsummit.HRM.model.User> targetSet = new HashSet<com.frsummit.HRM.model.User>();
		for (User u : originSet) {
			targetSet.add(ModelConverter.getServerVersion(u));
		}
		hrRecord.setHrRecordUser(targetSet);
		hrRecord.setId(apiHRRecord.getId());
		hrRecord.setLeaveBalance(apiHRRecord.getLeaveBalance());
		hrRecord.setLeaveBalanceMaternity(apiHRRecord.getLeaveBalanceMaternity());
		hrRecord.setLeaveBalanceOther(apiHRRecord.getLeaveBalanceOther());
		hrRecord.setLeaveBalancePersonal(apiHRRecord.getLeaveBalancePersonal());
		hrRecord.setLeaveBalancePlanned(apiHRRecord.getLeaveBalancePlanned());
		hrRecord.setLeaveBalanceSick(apiHRRecord.getLeaveBalanceSick());
		hrRecord.setLeaveBalanceVacation(apiHRRecord.getLeaveBalanceVacation());
		hrRecord.setTotalLeave(apiHRRecord.getTotalLeave());
		hrRecord.setTotalLeaveMaternity(apiHRRecord.getTotalLeaveMaternity());
		hrRecord.setTotalLeaveOther(apiHRRecord.getTotalLeaveOther());
		hrRecord.setTotalLeavePersonal(apiHRRecord.getTotalLeavePersonal());
		hrRecord.setTotalLeavePlanned(apiHRRecord.getTotalLeavePlanned());
		hrRecord.setTotalLeaveSick(apiHRRecord.getTotalLeaveSick());
		hrRecord.setTotalLeaveTaken(apiHRRecord.getTotalLeaveTaken());
		hrRecord.setTotalLeaveTakenMaternity(apiHRRecord.getTotalLeaveTakenMaternity());
		hrRecord.setTotalLeaveTakenOther(apiHRRecord.getTotalLeaveTakenOther());
		hrRecord.setTotalLeaveTakenPersonal(apiHRRecord.getTotalLeaveTakenPersonal());
		hrRecord.setTotalLeaveTakenPlanned(apiHRRecord.getTotalLeaveTakenPlanned());
		hrRecord.setTotalLeaveTakenSick(apiHRRecord.getTotalLeaveTakenSick());
		hrRecord.setTotalLeaveTakenVacation(apiHRRecord.getTotalLeaveTakenVacation());
		hrRecord.setTotalLeaveVacation(apiHRRecord.getTotalLeaveVacation());
		hrRecord.setUserId(apiHRRecord.getUserId());

		return hrRecord;
	}
	
	public static HRRecord getApiVersion(com.frsummit.HRM.model.HRRecord serverHRRecord) {
		HRRecord hrRecord = new com.frsummit.HRM.api.server.entity.HRRecord();
		Set<com.frsummit.HRM.model.User> originSet = serverHRRecord.getHrRecordUser();
		Set<User> targetSet = new HashSet<User>();
		for (com.frsummit.HRM.model.User u : originSet) {
			targetSet.add(ModelConverter.getApiVersion(u));
		}
		hrRecord.setHrRecordUser(targetSet);
		hrRecord.setId(serverHRRecord.getId());
		hrRecord.setLeaveBalance(serverHRRecord.getLeaveBalance());
		hrRecord.setLeaveBalanceMaternity(serverHRRecord.getLeaveBalanceMaternity());
		hrRecord.setLeaveBalanceOther(serverHRRecord.getLeaveBalanceOther());
		hrRecord.setLeaveBalancePersonal(serverHRRecord.getLeaveBalancePersonal());
		hrRecord.setLeaveBalancePlanned(serverHRRecord.getLeaveBalancePlanned());
		hrRecord.setLeaveBalanceSick(serverHRRecord.getLeaveBalanceSick());
		hrRecord.setLeaveBalanceVacation(serverHRRecord.getLeaveBalanceVacation());
		hrRecord.setTotalLeave(serverHRRecord.getTotalLeave());
		hrRecord.setTotalLeaveMaternity(serverHRRecord.getTotalLeaveMaternity());
		hrRecord.setTotalLeaveOther(serverHRRecord.getTotalLeaveOther());
		hrRecord.setTotalLeavePersonal(serverHRRecord.getTotalLeavePersonal());
		hrRecord.setTotalLeavePlanned(serverHRRecord.getTotalLeavePlanned());
		hrRecord.setTotalLeaveSick(serverHRRecord.getTotalLeaveSick());
		hrRecord.setTotalLeaveTaken(serverHRRecord.getTotalLeaveTaken());
		hrRecord.setTotalLeaveTakenMaternity(serverHRRecord.getTotalLeaveTakenMaternity());
		hrRecord.setTotalLeaveTakenOther(serverHRRecord.getTotalLeaveTakenOther());
		hrRecord.setTotalLeaveTakenPersonal(serverHRRecord.getTotalLeaveTakenPersonal());
		hrRecord.setTotalLeaveTakenPlanned(serverHRRecord.getTotalLeaveTakenPlanned());
		hrRecord.setTotalLeaveTakenSick(serverHRRecord.getTotalLeaveTakenSick());
		hrRecord.setTotalLeaveTakenVacation(serverHRRecord.getTotalLeaveTakenVacation());
		hrRecord.setTotalLeaveVacation(serverHRRecord.getTotalLeaveVacation());
		hrRecord.setUserId(serverHRRecord.getUserId());

		return hrRecord;
	}
	
	public static com.frsummit.HRM.model.EmergencyContact getServerVersion(EmergencyContact api_ec) {
		com.frsummit.HRM.model.EmergencyContact ec = new com.frsummit.HRM.model.EmergencyContact();
		ec.setAddress(api_ec.getAddress());
		ec.setName(api_ec.getAddress());
		ec.setPhone(api_ec.getPhone());
		ec.setUserId(api_ec.getUserId());

		return ec;
	}

	public static EmergencyContact getApiVersion(com.frsummit.HRM.model.EmergencyContact server_ec) {
		EmergencyContact ec = new EmergencyContact();
		ec.setAddress(server_ec.getAddress());
		ec.setName(server_ec.getAddress());
		ec.setPhone(server_ec.getPhone());
		ec.setUserId(server_ec.getUserId());

		return ec;
	}

	public static com.frsummit.HRM.model.Attendance getServerVersion(Attendance apiAttendance) {
		com.frsummit.HRM.model.Attendance attendance = new com.frsummit.HRM.model.Attendance();
		attendance.setAbsent(apiAttendance.getAbsent());
		attendance.setAttendanceLink1(apiAttendance.getAttendanceLink1());
		attendance.setAttendanceLink2(apiAttendance.getAttendanceLink2());
		attendance.setAttendanceLink3(apiAttendance.getAttendanceLink3());
		attendance.setAttendanceLink4(apiAttendance.getAttendanceLink4());
		attendance.setAttendanceRemark(apiAttendance.getAttendanceRemark());
		attendance.setAttendId(apiAttendance.getAttendId());
		attendance.setInTime(apiAttendance.getInTime());
		attendance.setLateBy(apiAttendance.getLateBy());
		attendance.setOutTime(apiAttendance.getOutTime());
		attendance.setOvertime(apiAttendance.getOvertime());
		attendance.setPerformance(apiAttendance.getPerformance());
		attendance.setShift(apiAttendance.getShift());
		attendance.setSignTime(apiAttendance.getSignTime());
		attendance.setStatus(apiAttendance.getStatus());
		attendance.setUserId(apiAttendance.getUserId());
		attendance.setWorkDuration(apiAttendance.getWorkDuration());
		attendance.setWorkHourInDay(apiAttendance.getWorkHourInDay());

		return attendance;
	}

	public static Attendance getApiVersion(com.frsummit.HRM.model.Attendance serverAttendance) {
		Attendance attendance = new Attendance();
		attendance.setAbsent(serverAttendance.getAbsent());
		attendance.setAttendanceLink1(serverAttendance.getAttendanceLink1());
		attendance.setAttendanceLink2(serverAttendance.getAttendanceLink2());
		attendance.setAttendanceLink3(serverAttendance.getAttendanceLink3());
		attendance.setAttendanceLink4(serverAttendance.getAttendanceLink4());
		attendance.setAttendanceRemark(serverAttendance.getAttendanceRemark());
		attendance.setAttendId(serverAttendance.getAttendId());
		attendance.setInTime(serverAttendance.getInTime());
		attendance.setLateBy(serverAttendance.getLateBy());
		attendance.setOutTime(serverAttendance.getOutTime());
		attendance.setOvertime(serverAttendance.getOvertime());
		attendance.setPerformance(serverAttendance.getPerformance());
		attendance.setShift(serverAttendance.getShift());
		attendance.setSignTime(serverAttendance.getSignTime());
		attendance.setStatus(serverAttendance.getStatus());
		attendance.setUserId(serverAttendance.getUserId());
		attendance.setWorkDuration(serverAttendance.getWorkDuration());
		attendance.setWorkHourInDay(serverAttendance.getWorkHourInDay());

		return attendance;
	}

}
