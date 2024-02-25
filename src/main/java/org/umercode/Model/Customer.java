package org.umercode.Model;

import java.time.LocalDate;

public class Customer {

    private int customerId;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthdate;


    public Customer() {
    }

    public Customer(int customerId, String name, String email,
                    String phone, LocalDate birthdate) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthdate = birthdate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString(){
        return getName() + ", " + getEmail();
    }
}
