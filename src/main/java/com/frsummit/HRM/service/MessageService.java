package com.frsummit.HRM.service;

import java.util.List;

import com.frsummit.HRM.model.Message;

public interface MessageService {
	public void saveMessage(Message message); // stl - exposed

	public List<Message> findMessage(); // stf - NOT to expose

	public List<Message> findMessage(String userId); // stl - exposed

	public List<Message> findMyALLMessage(); // stf - NOT to expose

	public List<Message> findALLMessageById(String userId); // NEW stl - exposed

	public List<Message> loadALLMessage(); // stl - exposed
}
