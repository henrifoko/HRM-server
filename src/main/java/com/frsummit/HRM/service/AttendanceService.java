package com.frsummit.HRM.service;

import java.util.List;

import com.frsummit.HRM.model.Attendance;

public interface AttendanceService {
	public void saveAttendance(Attendance attendance); // stl - exposed

	public void remarkAttendance(String attendId); // stl - exposed

	public void removeFromRemarkList(String attendId); // stl - exposed

	public void updateAttendance(Attendance attendance, String userId); // stl - exposed

	public List<Attendance> myAllSignList(); // stf - NOT to expose

	public List<Attendance> allSignList(String userId); // NEW - stl - exposed

	public List<Attendance> UsersSignList(); // stl - exposed

	public List<Attendance> remarkAttendanceList(); // stl - exposed

	public List<Attendance> attendanceStatus(String userId); // stl - exposed
}
