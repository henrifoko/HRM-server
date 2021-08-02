package com.frsummit.HRM.api.server.service;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.server.entity.Payroll;
import com.frsummit.HRM.api.server.util.ModelConverter;
import com.frsummit.HRM.service_impl.PayrollServiceImpl;

@Service("payrollServiceExposed")
public class PayrollServiceExposed extends PayrollServiceImpl {

	public void savePayroll(Payroll payroll) {
		super.savePayroll(ModelConverter.getLocalVersion(payroll));
	}
}
