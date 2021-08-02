package com.frsummit.HRM.api.server.service;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.server.entity.HRRecord;
import com.frsummit.HRM.api.server.util.ModelConverter;
import com.frsummit.HRM.service_impl.HRRecordServiceImpl;

@Service("hRRecordServiceExposed")
public class HRRecordServiceExposed extends HRRecordServiceImpl {

	public void saveHRRecord(HRRecord hrRecord) {
		super.saveHRRecord(ModelConverter.getLocalVersion(hrRecord));
	}
}
