package com.example.authz.domain;

public class Team extends TeamData {
    public int id;

    public Team(int id, TeamData data) {
        super(data.name, data.city);
        this.id = id;
    }
}
