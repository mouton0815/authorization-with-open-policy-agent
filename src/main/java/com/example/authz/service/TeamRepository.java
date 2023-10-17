package com.example.authz.service;

import com.example.authz.domain.Team;
import com.example.authz.domain.TeamData;

import java.util.Optional;
import java.util.stream.Stream;

public interface TeamRepository {
    Stream<Team> getTeams();
    Optional<Team> getTeam(int id);
    Team addTeam(TeamData team);
    Optional<Team> updateTeam(int id, TeamData teamData);
    boolean deleteTeam(int id);
}
