package com.frsummit.HRM.api.server.service;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.server.entity.Message;
import com.frsummit.HRM.api.server.util.ModelConverter;
import com.frsummit.HRM.service_impl.MessageServiceImpl;

@Service("messageServiceExposed")
public class MessageServiceExposed extends MessageServiceImpl {

	public void saveMessage(Message message) {
		super.saveMessage(ModelConverter.getLocalVersion(message));
	}
}
