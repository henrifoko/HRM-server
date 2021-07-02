package com.frsummit.HRM.api.rmi.service;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.rmi.model.Payroll;
import com.frsummit.HRM.service_impl.PayrollServiceImpl;

@Service("payrollServiceExposed")
public class PayrollServiceExposed extends PayrollServiceImpl {

	public void savePayroll(Payroll payroll) {
		super.savePayroll(payroll.toLocalModel());
	}
}
