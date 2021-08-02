package com.frsummit.HRM.api.server.service;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.server.entity.EmergencyContact;
import com.frsummit.HRM.api.server.util.ModelConverter;
import com.frsummit.HRM.service_impl.EmergencyContactServiceImpl;

@Service("emergencyContactServiceExposed")
public class EmergencyContactServiceExposed extends EmergencyContactServiceImpl {

	public void saveEmergencyContact(EmergencyContact emergencyContact) {
		super.saveEmergencyContact(ModelConverter.getLocalVersion(emergencyContact));
	}
}
