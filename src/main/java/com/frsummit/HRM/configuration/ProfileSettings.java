package com.frsummit.HRM.configuration;

import org.springframework.beans.factory.annotation.Autowired;

public class ProfileSettings {

    @Autowired
    private MyAuthorization myAuthorization;

    public void getOriginalName(String fn, String mn, String ln, String email, String dept, String desg, String dob, String sex,
                                String phone, String bg, String father, String mother, String nid, String passport,
                                String pr_h, String pr_st, String pr_po, String pr_city, String pr_dis, String pr_cntry,
                                String p_h, String p_st, String p_po, String p_city, String p_dis, String p_cntry){

        if(fn.equalsIgnoreCase("")) fn = myAuthorization.userFromEmailOrId().getFirstName();
        if(mn.equalsIgnoreCase("")) mn = myAuthorization.userFromEmailOrId().getMiddleName();
        if(ln.equalsIgnoreCase("")) ln = myAuthorization.userFromEmailOrId().getLastName();

        if(email.equalsIgnoreCase("")) email = myAuthorization.userFromEmailOrId().getEmail();
        if(dept.equalsIgnoreCase("")) dept = myAuthorization.userFromEmailOrId().getDepartment();
        if(desg.equalsIgnoreCase("")) desg = myAuthorization.userFromEmailOrId().getDesignation();
        if(dob.equalsIgnoreCase("")) dob = myAuthorization.userFromEmailOrId().getDob();
        if(sex.equalsIgnoreCase("")) sex = myAuthorization.userFromEmailOrId().getSex();
        if(phone.equalsIgnoreCase("")) phone = myAuthorization.userFromEmailOrId().getPhone();
        if(bg.equalsIgnoreCase("")) bg = myAuthorization.userFromEmailOrId().getBloodGroup();

        if(father.equalsIgnoreCase("")) father = myAuthorization.userFromEmailOrId().getFatherName();
        if(mother.equalsIgnoreCase("")) mother = myAuthorization.userFromEmailOrId().getMotherName();
        if(nid.equalsIgnoreCase("")) nid = myAuthorization.userFromEmailOrId().getNid();
        if(passport.equalsIgnoreCase("")) passport = myAuthorization.userFromEmailOrId().getPassword();

        if(pr_h.equalsIgnoreCase("")) pr_h = myAuthorization.userFromEmailOrId().getPresentAddress().getHouse();
        if(pr_st.equalsIgnoreCase("")) pr_st = myAuthorization.userFromEmailOrId().getPresentAddress().getStreet();
        if(pr_po.equalsIgnoreCase("")) pr_po = myAuthorization.userFromEmailOrId().getPresentAddress().getPostOffice();
        if(pr_city.equalsIgnoreCase("")) pr_city = myAuthorization.userFromEmailOrId().getPresentAddress().getCity();
        if(pr_dis.equalsIgnoreCase("")) pr_dis = myAuthorization.userFromEmailOrId().getPresentAddress().getDistrict();
        if(pr_cntry.equalsIgnoreCase("")) pr_cntry = myAuthorization.userFromEmailOrId().getPresentAddress().getCountry();

        if(p_h.equalsIgnoreCase("")) p_h = myAuthorization.userFromEmailOrId().getPermanentAddress().getHouse();
        if(p_st.equalsIgnoreCase("")) p_st = myAuthorization.userFromEmailOrId().getPermanentAddress().getStreet();
        if(p_po.equalsIgnoreCase("")) p_po = myAuthorization.userFromEmailOrId().getPermanentAddress().getPostOffice();
        if(p_city.equalsIgnoreCase("")) p_city = myAuthorization.userFromEmailOrId().getPermanentAddress().getCity();
        if(p_dis.equalsIgnoreCase("")) p_dis = myAuthorization.userFromEmailOrId().getPermanentAddress().getDistrict();
        if(p_cntry.equalsIgnoreCase("")) p_cntry = myAuthorization.userFromEmailOrId().getPermanentAddress().getCountry();
    }
}
