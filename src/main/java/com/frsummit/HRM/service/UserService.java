package com.frsummit.HRM.service;

import java.util.List;

import com.frsummit.HRM.api.server.entity.Address;
import com.frsummit.HRM.api.server.exception.ClientException;
import com.frsummit.HRM.model.User;

public interface UserService {

    // first Administration id
    public void createAdmin(); // stl - NOT TO expose

    public void updateAdmin(); // stl - NOT TO expose

    // Find
    public User findUserByName( String name ); // stl - NOT to expose

    public User findUserByEmail( String email ); // stl - exposed

    public User findUserById( String id ); // stl - exposed

    public List<User> findAllUsers(); // stl - exposed

    public List<User> findUserByDepartment( String dept ); // stl - exposeT

    // Save and Update
    public void saveUser( User user, String usrRole ); // stl - NOT TO expose

    public void saveUser( User user ); // NEW stl - NOT TO expose

    public void updateUser( String fn, String mn, String ln, String email, String dept, String desg, String dob,
            String sex,
            String phone, String bg, String father, String mother, String nid, String passport ); // stl
                                                                                                  // -
                                                                                                  // NOT
                                                                                                  // to
                                                                                                  // expose
    // String pr_h, String pr_st, String pr_po, String pr_city, String pr_dis,
    // String pr_cntry,
    // String p_h, String p_st, String p_po, String p_city, String p_dis, String
    // p_cntry);

    public void updateUser( String userId, String fn, String mn, String ln, String email, String dept, String desg,
            String dob, String sex, String phone, String bg, String father, String mother, String nid, String passport,
            Address psa, Address pma, String nationality, String role, String imageName, String incomeTexNo,
            int active ); // NEW stl - exposed

    public void updateUserProperty( String userId, String prop, Object value ); // NEW
                                                                                // stl
                                                                                // -
                                                                                // exposed

    public User getUserId( String email ); // stl - NOT TO expose

    public void myMail(); // NOT stf - NOT TO expose

    public List<User> findAllUsersId(); // stf - NOT TO expose

    public void deleteUser( String userId ); // stl - exposed

    // New services
    public User createNewUser( User user ) throws ClientException; // Créer un
                                                                   // nouvel
                                                                   // utilisateur
                                                                   // - TO BE
                                                                   // EXPOSED

}
