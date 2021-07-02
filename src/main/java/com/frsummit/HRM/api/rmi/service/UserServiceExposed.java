package com.frsummit.HRM.api.rmi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.rmi.FakeSession;
import com.frsummit.HRM.api.rmi.model.User;
import com.frsummit.HRM.service_impl.UserServiceImpl;

@Service("userServiceExposed")
public class UserServiceExposed extends UserServiceImpl {

	@Autowired
	private FakeSession session;

	public UserServiceExposed() {
		// TODO Auto-generated constructor stub
	}

	public void saveUser(User apiUser, String currentUserId) {
		long requestId = session.getFreeRequestId();
		
		session.bind(requestId, currentUserId);

		super.saveUser(apiUser.toLocalModel(), apiUser.getMyRole());
	}

	public void updateUser(User user, String currentUserId) {
		// session.setId(currentUserId);

		super.updateUser(user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getEmail(),
				user.getDepartment(), user.getDesignation(), user.getDob(), user.getSex(), user.getPhone(),
				user.getBloodGroup(), user.getFatherName(), user.getMotherName(), user.getNid(),
				user.getPassportNumber());
	}

	@Override
	public String myId() {
		// return sesion.getId(); // API admin user
		return "API_ADMIN";
	}
}
