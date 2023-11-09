package com.example.demo.domain;

public record Team(int id, String name, String city) {
    public Team(int id, TeamData data) {
        this(id, data.name(), data.city());
    }
}
