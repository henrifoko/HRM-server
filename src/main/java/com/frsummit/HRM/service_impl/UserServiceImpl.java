package com.frsummit.HRM.service_impl;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frsummit.HRM.api.server.entity.Address;
import com.frsummit.HRM.crud_repository.RoleRepository;
import com.frsummit.HRM.crud_repository.UserRepository;
import com.frsummit.HRM.model.Attendance;
import com.frsummit.HRM.model.HRRecord;
import com.frsummit.HRM.model.Role;
import com.frsummit.HRM.model.User;
import com.frsummit.HRM.service.AttendanceService;
import com.frsummit.HRM.service.HRRecordService;
import com.frsummit.HRM.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private HRRecordService hrRecordService;
	@Autowired
	private AttendanceService attendanceService;

	@PersistenceContext
	private EntityManager entityManager;

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	AuthenticationManagerBuilder auth;

	@Value("${spring.queries.users-id-query}")
	private String query1;

//    @Value("${spring.queries.users-list}")
//    private String usersListQuery;

	JdbcTemplate template;
	private User eml, i, nm;
	String e, iii;

	@Override
	public User findUserByEmail(String email) {

//        eml = userRepository.findByEmail(email);
//        e = eml.getEmail().toString();
		return userRepository.findByEmail(email);
	}

	@Override
	public User findUserById(String id) {
//        i = userRepository.findById(id);
//        e = i.getId().toString();
//        //j = i.getId();
		return userRepository.findById(id);
	}

	@Override
	public List<User> findUserByDepartment(String dept) {
		return entityManager.createQuery("select u from User as u where u.department= '" + dept + "'", User.class)
				.getResultList();
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void myMail() {
		// System.out.println(eml.getEmail());
//        System.out.println(i.getId());
//        System.out.println(e);
		// System.out.println(nm.getName());

//        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User AS u WHERE u.user_id=:id", User.class);
//        query.executeUpdate();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// User user = userService.findUserByEmail(auth.getName());
		User u = userRepository.findByEmail(auth.getName());

		iii = u.getId().toString();
		System.out.println("This is start");
		System.out.println("This is u : " + u);
		System.out.println("This is u.get : " + iii);

//        String ss = eml.getEmail();
//        String[] parts = ss.split("@");
//        System.out.println(parts[0] + " @ " + parts[1]);

	}

	@Override
	@Modifying
//    @Query
	public void updateUser(String fn, String mn, String ln, String email, String dept, String desg, String dob,
			String sex, String phone, String bg, String father, String mother, String nid, String passport) {
//                           String pr_h, String pr_st, String pr_po, String pr_city, String pr_dis, String pr_cntry,
//                           String p_h, String p_st, String p_po, String p_city, String p_dis, String p_cntry) {

		Query query = entityManager.createQuery("UPDATE User u SET " + "u.firstName = '" + fn + "', u.middleName = '"
				+ mn + "', u.lastName = '" + ln + "', u.email = '" + email + "', u.department = '" + dept
				+ "', u.designation = '" + desg + "', u.dob = '" + dob + "', u.sex = '" + sex + "', u.phone = '" + phone
				+ "', u.bloodGroup = '" + bg + "', u.fatherName = '" + father + "', u.motherName = '" + mother
				+ "', u.nid = '" + nid + "', u.passportNumber = '" + passport +
				/*
				 * "u.presentAddress.house = '" + pr_h + "u.presentAddress.street = '" + pr_st +
				 * "u.presentAddress.postOffice = '" + pr_po + "u.presentAddress.city = '" +
				 * pr_city + "u.presentAddress.district = '" + pr_dis +
				 * "u.presentAddress.country = '" + pr_cntry + "u.permanentAddress.house = '" +
				 * p_h + "u.permanentAddress.street = '" + p_st +
				 * "u.permanentAddress.postOffice = '" + p_po + "u.permanentAddress.city = '" +
				 * p_city + "u.permanentAddress.district = '" + p_dis +
				 * "u.permanentAddress.country = '" + p_cntry +
				 */ "' WHERE u.id='" + myId() + "'");
		// query.setParameter("email", email);
		query.executeUpdate();
//        if(email == myId())
//            userRepository.updateName(email, name);
//        else
//            System.out.println("Fail");

//        TypedQuery<User> query = entityManager.createQuery("UPDATE User u SET u.name = :name WHERE u.email='" + eml.getEmail() +"'", User.class);
		/*
		 * Query query = entityManager.createQuery("UPDATE User u SET u.name = '" + name
		 * + "' , u.lastName = '" + lastName + "' WHERE u.id='" + myId() +"'");
		 * //query.setParameter("email", email); query.executeUpdate();
		 */
//
//        User u = new User(email, name);
//        u.setId(u.getId());
//        u.setName(name);
//        userRepository.save(u);
	}

	public String myId() {

		String myId;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// User user = userService.findUserByEmail(auth.getName());
		User u = userRepository.findByEmail(auth.getName());
		myId = u.getId().toString();
		// myId = (Integer.parseInt( u.getId()));
		// myId = u.getEmail().toString();
		return myId;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void createAdmin() {
		List<User> userList = findAllUsers();
		System.out.println(userList.size());
		if (userList.size() == 0) {
			User user = new User();
			user.setId("0000");
			user.setFirstName("Admin");
			user.setMiddleName("Admin");
			user.setLastName("Admin");
			user.setEmail("admin@hrm");
			user.setPassword("admin");
			saveUser(user, "ADMIN");
		}
	}

	@Override
	@Modifying
	public void updateAdmin() {
		if (findAllUsers().size() == 1) {
			Query query = entityManager.createQuery("UPDATE User u SET u.email = 'admin' WHERE u.id='0000'");
			query.executeUpdate();
		}
	}

	@Override
	public User findUserByName(String name) {
//        nm =  userRepository.findByName(name);
//        return userRepository.findByName(name);
		return null;
	}

	@Override
	public void saveUser(User user, String usrRole) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole(usrRole);
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		user.setMyRole(usrRole);
		userRepository.save(user);
		System.out.println(user);
	}

	public void saveUser(User user) {
		saveUser(user, user.getMyRole());
	}

	public List<User> findAllUsers() {
		return entityManager.createQuery("SELECT u FROM User AS u", User.class).getResultList();
//        return entityManager.createQuery(usersListQuery, User.class).getResultList();
		// return null;
	}

	public List<User> findAllUsersId() {
		return entityManager.createQuery("SELECT u FROM User AS u WHERE u.id= '" + myId() + "'", User.class)
				.getResultList();
	}

	@Override
	@Modifying
	public void deleteUser(String userId) {
		Query query = entityManager.createQuery("DELETE FROM User WHERE id='" + userId + "'");
		// query.setParameter("email", email);
		query.executeUpdate();
		// userRepository.delete(userId);
	}

	/*
	 * @Override public void update(User user) {
	 * 
	 * // String sql =
	 * "UPDATE User SET ID=:id, NAME=:name, LASTNAME=:lastName WHERE email=:email";
	 * // namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user)); //
	 * System.out.println(sql); //User eml= findUserByEmail(user.getEmail());
	 * //System.out.println(eml);
	 * 
	 * //entityManager.merge(user);
	 * 
	 * //String sql="update User set id="+user.getId()+", namae='"+user.getName()
	 * +"', last_name='"+user.getLastName()+"' where email='"+user.getEmail()+"'";
	 * //return template.update(sql); //user.setActive(1);
	 * //userRepository.save(user); //System.out.println(user);
	 * 
	 * 
	 * }
	 */

	private SqlParameterSource getSqlParameterByModel(User user) {

		// Unable to handle List<String> or Array
		// BeanPropertySqlParameterSource

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", user.getId());
		paramSource.addValue("name", user.getFirstName());
		paramSource.addValue("lastName", user.getLastName());
		return paramSource;
	}

	public User getUserId(String email) {
		TypedQuery<User> query = entityManager.createQuery("SELECT u.user_id FROM User AS u WHERE e.email=:email",
				User.class);
		query.setParameter("email", email);

		return getSingleResultOrNull(query);
	}

	private User getSingleResultOrNull(TypedQuery<User> query) {
		query.setMaxResults(1);
		List<User> list = query.getResultList();
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	@Modifying
	public void updateUser(String userId, String fn, String mn, String ln, String email, String dept, String desg,
			String dob, String sex, String phone, String bg, String father, String mother, String nid, String passport,
			Address psa, Address pma, String nationality, String role, String imageName, String incomeTexNo,
			int active) {
		String updateQuery = "UPDATE User u SET ";
		if (fn != null) {
			updateQuery += "u.firstName = '" + fn + "', ";
		}
		if (mn != null) {
			updateQuery += "u.middleName = '" + mn + "', ";
		}
		if (ln != null) {
			updateQuery += "u.lastName = '" + ln + "', ";
		}
		if (email != null) {
			updateQuery += "u.email = '" + email + "', ";
		}
		if (phone != null) {
			updateQuery += "u.phone = '" + phone + "', ";
		}
		if (dob != null) {
			updateQuery += "u.dob = '" + dob + "', ";
		}
		if (psa != null) {
			String city, country, house, street, district, postOffice;
			if ((city = psa.getCity()) != null) {
				updateQuery += "u.presentAddress.city = '" + city + "', ";
			}
			if ((country = psa.getCountry()) != null) {
				updateQuery += "u.presentAddress.country = '" + country + "', ";
			}
			if ((district = psa.getDistrict()) != null) {
				updateQuery += "u.presentAddress.district = '" + district + "', ";
			}
			if ((house = psa.getHouse()) != null) {
				updateQuery += "u.presentAddress.house = '" + house + "', ";
			}
			if ((street = psa.getStreet()) != null) {
				updateQuery += "u.presentAddress.street = '" + street + "', ";
			}
			if ((postOffice = psa.getPostOffice()) != null) {
				updateQuery += "u.presentAddress.postOffice= '" + postOffice + "', ";
			}
		}
		if (pma != null) {
			String city, country, house, street, district, postOffice;
			if ((city = pma.getCity()) != null) {
				updateQuery += "u.permanentAddress.city = '" + city + "', ";
			}
			if ((country = pma.getCountry()) != null) {
				updateQuery += "u.permanentAddress.country = '" + country + "', ";
			}
			if ((district = pma.getDistrict()) != null) {
				updateQuery += "u.permanentAddress.district = '" + district + "', ";
			}
			if ((house = pma.getHouse()) != null) {
				updateQuery += "u.permanentAddress.house = '" + house + "', ";
			}
			if ((street = pma.getStreet()) != null) {
				updateQuery += "u.permanentAddress.street = '" + street + "', ";
			}
			if ((postOffice = pma.getPostOffice()) != null) {
				updateQuery += "u.permanentAddress.postOffice= '" + postOffice + "', ";
			}
		}
		if (desg != null) {
			updateQuery += "u.designation = '" + desg + "', ";
		}
		if (dept != null) {
			updateQuery += "u.department = '" + dept + "', ";
		}
		if (imageName != null) {
			updateQuery += "u.imageName = '" + imageName + "', ";
		}
		if (bg != null) {
			updateQuery += "u.bloodGroup = '" + bg + "', ";
		}
		if (sex != null) {
			updateQuery += "u.sex = '" + sex + "', ";
		}
		if (father != null) {
			updateQuery += "u.fatherName = '" + father + "', ";
		}
		if (mother != null) {
			updateQuery += "u.motherName = '" + mother + "', ";
		}
		if (nid != null) {
			updateQuery += "u.nid = '" + nid + "', ";
		}
		if (passport != null) {
			updateQuery += "u.passportNumber = '" + passport + "', ";
		}
		if (nationality != null) {
			updateQuery += "u.nationality = '" + nationality + "', ";
		}
		if (incomeTexNo != null) {
			updateQuery += "u.incomeTexNo = '" + incomeTexNo + "', ";
		}
		if (role != null) {
			updateQuery += "u.incomeTexNo = '" + role + "', ";
		}
		updateQuery += "u.active = '" + active + "' ";
		updateQuery += "' WHERE u.id='" + userId + "'";

		Query query = entityManager.createQuery(updateQuery);

		query.executeUpdate();
	}

	@Override
	@Modifying
	public void updateUserProperty(String userId, String prop, Object value) {
		String updateQuery = "UPDATE User u SET ";
		switch (prop) {
		case "firstName":
			updateQuery += "u.firstName = '" + value.toString();
			break;
		case "middleName":
			updateQuery += "u.middleName = '" + value.toString();
			break;
		case "lastName":
			updateQuery += "u.lastName = '" + value.toString();
			break;
		case "email":
			updateQuery += "u.email = '" + value.toString();
			break;
		case "phone":
			updateQuery += "u.phone = '" + value.toString();
			break;
		case "dob":
			updateQuery += "u.dob = '" + value.toString();
			break;
		case "presentAddress":
			Address presentAddress = (Address) value;
			updateQuery += "u.presentAddress.city = '";
			updateQuery += presentAddress.getCity() + "', ";
			updateQuery += "u.presentAddress.country = '";
			updateQuery += presentAddress.getCountry() + "', ";
			updateQuery += "u.presentAddress.district = '";
			updateQuery += presentAddress.getDistrict() + "', ";
			updateQuery += "u.presentAddress.house = '";
			updateQuery += presentAddress.getHouse() + "', ";
			updateQuery += "u.presentAddress.street = '";
			updateQuery += presentAddress.getStreet() + "', ";
			updateQuery += "u.presentAddress.postOffice = '";
			updateQuery += presentAddress.getPostOffice();
			break;
		case "permanentAddress":
			Address permanentAddress = (Address) value;
			updateQuery += "u.permanentAddress.city = '";
			updateQuery += permanentAddress.getCity() + "', ";
			updateQuery += "u.permanentAddress.country = '";
			updateQuery += permanentAddress.getCountry() + "', ";
			updateQuery += "u.permanentAddress.district = '";
			updateQuery += permanentAddress.getDistrict() + "', ";
			updateQuery += "u.permanentAddress.house = '";
			updateQuery += permanentAddress.getHouse() + "', ";
			updateQuery += "u.permanentAddress.street = '";
			updateQuery += permanentAddress.getStreet() + "', ";
			updateQuery += "u.permanentAddress.postOffice = '";
			updateQuery += permanentAddress.getPostOffice();
			break;
		case "designation":
			updateQuery += "u.designation = '" + value.toString();
			break;
		case "department":
			updateQuery += "u.department = '" + value.toString();
			break;
		case "imageName":
			updateQuery += "u.imageName = '" + value.toString();
			break;
		case "bloodGroup":
			updateQuery += "u.bloodGroup = '" + value.toString();
			break;
		case "sex":
			updateQuery += "u.sex = '" + value.toString();
			break;
		case "fatherName":
			updateQuery += "u.fatherName = '" + value.toString();
			break;
		case "motherName":
			updateQuery += "u.motherName = '" + value.toString();
			break;
		case "nid":
			updateQuery += "u.nid = '" + value.toString();
			break;
		case "passportNumber":
			updateQuery += "u.passportNumber = '" + value.toString();
			break;
		case "nationality":
			updateQuery += "u.nationality = '" + value.toString();
			break;
		case "incomeTexNo":
			updateQuery += "u.incomeTexNo = '" + value.toString();
			break;
		case "myRole":
			updateQuery += "u.myRole = '" + value.toString();
			break;
		}
		updateQuery += "' WHERE u.id='" + userId + "'";

		Query query = entityManager.createQuery(updateQuery);

		query.executeUpdate();
	}

	@Override
	public User createNewUser(User user) {
		User userExists = this.findUserByEmail(user.getEmail());
		String rl = "";
		if (userExists != null) {
			return null;
		}
		if (user.getMyRole() == "") {
			rl = "USER";
			String userRole = rl.toUpperCase();
			this.saveUser(user, userRole);
			HRRecord hrRecord = new HRRecord(user.getId(), 30, 0, 30, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5,
					5);

			hrRecordService.saveHRRecord(hrRecord);
			Attendance attendance = new Attendance();
			attendance.setStatus("IN");
			attendance.setUserId(user.getId());
			attendanceService.saveAttendance(attendance);
		}

		return this.findUserById(user.getId());
	}
}
