package com.frsummit.HRM.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "emergency_contact")
public class EmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String Address;

    @Column(name = "phone")
    private String phone;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "emergency_contact_user", joinColumns = @JoinColumn(name = "emergency_contact_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userEmergencyContact;

    public EmergencyContact() {
    }

    public EmergencyContact(String name, String address, String phone) {
        this.name = name;
        Address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<User> getUserEmergencyContact() {
        return userEmergencyContact;
    }

    public void setUserEmergencyContact(Set<User> userEmergencyContact) {
        this.userEmergencyContact = userEmergencyContact;
    }
}
