package com.frsummit.HRM.api.rmi.service;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.rmi.model.Attendance;
import com.frsummit.HRM.service_impl.AttendanceServiceImpl;

@Service("attendanceServiceExposed")
public class AttendanceServiceExposed extends AttendanceServiceImpl {

	public void saveAttendance(Attendance attendance) {
		super.saveAttendance(attendance.toLocalModel());
	}

	public void updateAttendance(Attendance attendance, String userId) {
		super.updateAttendance(attendance.toLocalModel(), userId);
	}
}
