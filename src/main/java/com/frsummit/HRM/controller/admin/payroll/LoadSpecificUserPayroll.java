package com.frsummit.HRM.controller.admin.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.frsummit.HRM.service.PayrollService;

@Controller
public class LoadSpecificUserPayroll {

    @Autowired
    private PayrollService payrollService;

    @RequestMapping(value = "/admin/payroll-load-specific-user", method = RequestMethod.GET)
    public String specificUserPayrollLoad(){
        return "payroll_load_specific_user";
    }

    @RequestMapping(value = "/admin/load-specific-payroll", method = RequestMethod.GET)
    public String loadSpecificUserPayroll(@RequestParam(value = "userId") String userId, Model model){
        model.addAttribute("userPayrollHistory", payrollService.findSpecificUserPayroll(userId));
        return "payroll_load_specific_list";
    }
}
