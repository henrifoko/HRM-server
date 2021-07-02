package com.frsummit.HRM.api.rmi;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.service.AttendanceService;
import com.frsummit.HRM.service.EmergencyContactService;
import com.frsummit.HRM.service.HRRecordService;
import com.frsummit.HRM.service.LeaveService;
import com.frsummit.HRM.service.LinkService;
import com.frsummit.HRM.service.MessageService;
import com.frsummit.HRM.service.PayrollService;
import com.frsummit.HRM.service.RoleService;
import com.frsummit.HRM.service.TestImgService;
import com.frsummit.HRM.service.UserService;

@Service
public class ServiceWrapper {

	private static AttendanceService attendanceService;
	private static EmergencyContactService emergencyContactService;
	private static HRRecordService hRRecordService;
	private static LeaveService leaveService;
	private static LinkService linkService;
	private static MessageService messageService;
	private static PayrollService payrollService;
	private static RoleService roleService;
	private static TestImgService testImgService;
	private static UserService userService;

//	@Autowired
//	public ServiceWrapper(AttendanceService as, EmergencyContactService ecs, HRRecordService hrs, LeaveService ls,
//			LinkService lks, MessageService ms, PayrollService ps, RoleService rs, TestImgService tis,
//			@Qualifier("userServiceExposed") UserService us) {
//		attendanceService = as;
//		emergencyContactService = ecs;
//		hRRecordService = hrs;
//		leaveService = ls;
//		linkService = lks;
//		messageService = ms;
//		payrollService = ps;
//		roleService = rs;
//		testImgService = tis;
//		userService = us;
//	}

	public static Object getService(String name) throws BeanNotFoundException {
		if (name.equals("attendanceService")) {
			return attendanceService;
		} else if (name.equals("emergencyContactService")) {
			return emergencyContactService;
		} else if (name.equals("hRRecordService")) {
			return hRRecordService;
		} else if (name.equals("leaveService")) {
			return leaveService;
		} else if (name.equals("linkService")) {
			return linkService;
		} else if (name.equals("messageService")) {
			return messageService;
		} else if (name.equals("payrollService")) {
			return payrollService;
		} else if (name.equals("roleService")) {
			return roleService;
		} else if (name.equals("testImgService")) {
			return testImgService;
		} else if (name.equals("userService")) {
			return userService;
		} else {
			throw new BeanNotFoundException("The bean " + name + " was not found in the serviceWrapper");
		}
	}
}
