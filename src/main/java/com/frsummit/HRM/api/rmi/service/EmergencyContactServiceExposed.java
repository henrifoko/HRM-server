package com.frsummit.HRM.api.rmi.service;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.rmi.model.EmergencyContact;
import com.frsummit.HRM.service_impl.EmergencyContactServiceImpl;

@Service("emergencyContactServiceExposed")
public class EmergencyContactServiceExposed extends EmergencyContactServiceImpl {

	public void saveEmergencyContact(EmergencyContact emergencyContact) {
		super.saveEmergencyContact(emergencyContact.toLocalModel());
	}
}
