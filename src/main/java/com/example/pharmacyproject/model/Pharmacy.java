package com.example.pharmacyproject.model;

import java.io.Serializable;

public class Pharmacy extends BaseModel implements Serializable {
    private String name;
    private String phone_number;
    private String address;
    private String working_hours;

    public Pharmacy() {
    }

    public Pharmacy(int id, String name, String phone_number, String address, String working_hours) {
        super(id);
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
        this.working_hours = working_hours;

    }

    public Pharmacy(String name, String phone_number, String address, String working_hours) {
        super();
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
        this.working_hours = working_hours;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(String working_hours) {
        this.working_hours = working_hours;
    }
}
