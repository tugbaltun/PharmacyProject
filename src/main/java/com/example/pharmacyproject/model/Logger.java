package com.example.pharmacyproject.model;

import java.io.Serializable;

public class Logger extends BaseModel implements Serializable {
    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Logger() {
    }

    public Logger(String value) {
        this.value = value;
    }

    public Logger(int id, String value) {
        super(id);
        this.value = value;
    }
}
