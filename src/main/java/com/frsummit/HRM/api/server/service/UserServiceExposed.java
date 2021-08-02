package com.frsummit.HRM.api.server.service;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.server.entity.User;
import com.frsummit.HRM.api.server.util.ModelConverter;
import com.frsummit.HRM.service_impl.UserServiceImpl;

@Service("userServiceExposed")
public class UserServiceExposed extends UserServiceImpl {

	public UserServiceExposed() {
		// TODO Auto-generated constructor stub
	}

	public void saveUser(User user) {
		super.saveUser(ModelConverter.getLocalVersion(user));
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
