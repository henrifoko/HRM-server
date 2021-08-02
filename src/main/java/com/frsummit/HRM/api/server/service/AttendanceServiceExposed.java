package com.frsummit.HRM.api.server.service;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.server.entity.Attendance;
import com.frsummit.HRM.api.server.util.ModelConverter;
import com.frsummit.HRM.service_impl.AttendanceServiceImpl;

@Service("attendanceServiceExposed")
public class AttendanceServiceExposed extends AttendanceServiceImpl {

	public void saveAttendance(Attendance attendance) {
		super.saveAttendance(ModelConverter.getLocalVersion(attendance));
	}

	public void updateAttendance(Attendance attendance, String userId) {
		super.updateAttendance(ModelConverter.getLocalVersion(attendance), userId);
	}
}
