package com.frsummit.HRM.service;

import java.util.List;

import com.frsummit.HRM.model.Payroll;

public interface PayrollService {

	public void savePayroll(Payroll payroll); // stl - exposed

//////////////////////////////////////
/////    Admin Section    ////////////
//////////////////////////////////////

	public List<Payroll> findAllUsersPayroll(); // stl - exposed

	public List<Payroll> findSpecificUserPayroll(String userId); // stl - exposed

//////////////////////////////////////
/////    User Section    ////////////
//////////////////////////////////////

	public List<Payroll> findUserCurrentPayroll(String userId); // stl - NOT to expose

	public List<Payroll> findUserPayrollHistory(String userId); // stl - exposed
}
