package com.frsummit.HRM.api.server.entity;

import java.io.Serializable;

@SuppressWarnings( "serial" )
public class EmergencyContact implements Serializable {

    private int    leaveId;
    private String name;

    private String address;

    private String phone;

    public EmergencyContact() {
        super();
    }

    public EmergencyContact( int leaveId, String name, String address, String phone ) {
        super();
        this.leaveId = leaveId;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId( int leaveId ) {
        this.leaveId = leaveId;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress( String address ) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone( String phone ) {
        this.phone = phone;
    }

    // @ManyToMany(cascade = CascadeType.ALL)
    // @JoinTable(name = "emergency_contact_user", joinColumns =
    // @JoinColumn(name = "emergency_contact_id"), inverseJoinColumns =
    // @JoinColumn(name = "user_id"))
    // private Set<User> userEmergencyContact;

}
