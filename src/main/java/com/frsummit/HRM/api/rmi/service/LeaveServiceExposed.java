package com.frsummit.HRM.api.rmi.service;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.rmi.model.Leaves;
import com.frsummit.HRM.service_impl.LeaveServiceImpl;

@Service("leaveServiceExposed")
public class LeaveServiceExposed extends LeaveServiceImpl {

	public void saveLeave(Leaves leaves) {
		super.saveLeave(leaves.toLocalModel());
	}
}
