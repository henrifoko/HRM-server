package com.frsummit.HRM.api.rmi.service;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.rmi.model.HRRecord;
import com.frsummit.HRM.service_impl.HRRecordServiceImpl;

@Service("hRRecordServiceExposed")
public class HRRecordServiceExposed extends HRRecordServiceImpl {

	public void saveHRRecord(HRRecord hrRecord) {
		super.saveHRRecord(hrRecord.toLocalModel());
	}
}
