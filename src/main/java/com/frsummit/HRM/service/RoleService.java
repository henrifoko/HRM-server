package com.frsummit.HRM.service;

import java.util.List;

import com.frsummit.HRM.model.Role;

public interface RoleService {

	public List<Role> findAllRole(String role); // stl - exposed

	public List<Role> findAllRoles(); // stl - exposed

	public void insertRoleAdmin(Role role); // stl - NOT to expose
}
