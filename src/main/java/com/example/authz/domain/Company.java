package com.example.authz.domain;

public class Company extends CompanyData {
    public int id;

    public Company(int id, CompanyData data) {
        super(data.name, data.country);
        this.id = id;
    }
}
