package com.example.authz.domain;

public class TeamData {
    public String name;
    public String city;

    public TeamData(String name, String city) {
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return "TeamData{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
