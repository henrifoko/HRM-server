package com.frsummit.HRM.service;

import java.util.List;

import com.frsummit.HRM.model.HRRecord;

public interface HRRecordService {

	public void saveHRRecord(HRRecord hrRecord); // stl - exposed

	public List<HRRecord> getAllRecord(String userId); // stl - exposed

	public List<HRRecord> getAllRecordByDept(String department); // stl - exposed

	public List<HRRecord> findAllHRRecords(); // stl - exposed

	public void updateHRRecord(String userId, String leaveName, int totalLeaveTaken, int totalLeaveBalance); // stl -
																												// exposed
}
