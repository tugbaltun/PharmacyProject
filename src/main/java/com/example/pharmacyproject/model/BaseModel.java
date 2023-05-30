package com.example.pharmacyproject.model;

public  class BaseModel {
    private int id;

    public BaseModel(int id) {
        this.id = id;
    }

    public BaseModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
