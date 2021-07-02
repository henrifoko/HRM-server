package com.frsummit.HRM.api.rmi.service;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.rmi.model.Message;
import com.frsummit.HRM.service_impl.MessageServiceImpl;

@Service("messageServiceExposed")
public class MessageServiceExposed extends MessageServiceImpl {

	public void saveMessage(Message message) {
		super.saveMessage(message.toLocalModel());
	}
}
