package com.frsummit.HRM.api.server.service;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.server.entity.Leaves;
import com.frsummit.HRM.api.server.entity.User;
import com.frsummit.HRM.api.server.util.ModelConverter;
import com.frsummit.HRM.service_impl.LeaveServiceImpl;

@Service("leaveServiceExposed")
public class LeaveServiceExposed extends LeaveServiceImpl {

	public void saveLeave(Leaves leaves) {
		super.saveLeave(ModelConverter.getLocalVersion(leaves));
	}

	public byte[] printIndividualReport(User user) {
		return super.printIndividualReport(ModelConverter.getLocalVersion(user));
	}
}
