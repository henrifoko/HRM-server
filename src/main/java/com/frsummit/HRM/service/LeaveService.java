package com.frsummit.HRM.service;

import com.frsummit.HRM.model.Leaves;

import java.util.List;

public interface LeaveService {

    public void saveLeave(Leaves leaves);
    public void updateLeave(String leaveId, String leaveStatus);

//////////////////////////////////////
/////    Admin Section    ////////////
//////////////////////////////////////
    public List<Leaves> findAllLeaves();
    public List<Leaves> findAllLeavesByRole(String userRole);
    public List<Leaves> findAllRecentLeavesByRole(String userRole);

//////////////////////////////////////
/////    User Section    ////////////
//////////////////////////////////////
    public List<Leaves> findMyAllLeaves(String userId);
    public void cancelLeave(int id);
}
