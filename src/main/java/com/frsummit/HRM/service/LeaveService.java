package com.frsummit.HRM.service;

import java.util.List;

import com.frsummit.HRM.model.Leaves;

public interface LeaveService {

	public void saveLeave(Leaves leaves); // stl - exposed

	public void updateLeave(String leaveId, String leaveStatus); // stl - exposed

//////////////////////////////////////
/////    Admin Section    ////////////
//////////////////////////////////////
	public List<Leaves> findAllLeaves(); // stl - exposed

	public List<Leaves> findAllLeavesByRole(String userRole); // stl - exposed

	public List<Leaves> findAllRecentLeavesByRole(String userRole); // stl - exposed

	public void updateLeaveStatus(String leaveId, String selectStatus, String leaveActionBy, String modifyTo); // stl -
																												// exposed

	public List<Leaves> findLeavesByLeaveId(int id); // stl - exposed

	public List<Leaves> findLeavesByUserId(String userId); // stl - exposed

	public List<Leaves> findAllLeavesByUserId(String userId); // stl - exposed


    //////////////////////////////////////
/////    User Section    ////////////
//////////////////////////////////////
	public List<Leaves> findMyAllLeaves(String userId); // stl - NOT to expose

	public void cancelLeave(int id); // stl - exposed
}
