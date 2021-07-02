package com.frsummit.HRM.service;

import java.util.List;

import com.frsummit.HRM.model.User;

public interface UserService {

//    first Administration id
	public void createAdmin(); // stl - NOT to expose

	public void updateAdmin(); // stl - NOT to expose

//    Find
	public User findUserByName(String name); // stl - NOT to expose

	public User findUserByEmail(String email); // stl - exposed

	public User findUserById(String id); // stl - exposed

	public List<User> findAllUsers(); // stl - exposed

	public List<User> findUserByDepartment(String dept); // stl - exposed

//    Save and Update
	public void saveUser(User user, String usrRole); // stl - exposed
    public void updateUser(String fn, String mn, String ln, String email, String dept, String desg, String dob, String sex,
			String phone, String bg, String father, String mother, String nid, String passport); // stl - NOT to expose
//                       String pr_h, String pr_st, String pr_po, String pr_city, String pr_dis, String pr_cntry,
//                       String p_h, String p_st, String p_po, String p_city, String p_dis, String p_cntry);

	public void updateUser(String userId, String fn, String mn, String ln, String email, String dept, String desg,
			String dob, String sex, String phone, String bg, String father, String mother, String nid, String passport); // NEW
																															// stl
																															// -
																															// exposed

	public User getUserId(String email); // stl - NOT to expose

	public void myMail(); // NOT stf - NOT to expose

	public List<User> findAllUsersId(); // stf - NOT to expose

	public void deleteUser(String userId); // stl - exposed
}
