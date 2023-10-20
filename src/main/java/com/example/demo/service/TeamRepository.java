package com.example.demo.service;

import com.example.demo.domain.Team;
import com.example.demo.domain.TeamData;

import java.util.Optional;
import java.util.stream.Stream;

public interface TeamRepository {
    Stream<Team> getTeams();
    Optional<Team> getTeam(int id);
    Team addTeam(TeamData team);
    Optional<Team> updateTeam(int id, TeamData teamData);
    boolean deleteTeam(int id);
}
