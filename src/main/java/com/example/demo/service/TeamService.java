package com.example.demo.service;

import com.example.demo.domain.Team;
import com.example.demo.domain.TeamData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {
    private final TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public List<Team> getTeams() {
        return repository.getTeams().collect(Collectors.toList());
    }

    public Optional<Team> getTeam(int id) {
        return repository.getTeam(id);
    }

    public Team addTeam(TeamData teamData) {
        return repository.addTeam(teamData);
    }

    public Optional<Team> updateTeam(int id, TeamData teamData) {
        return repository.updateTeam(id, teamData);
    }

    public boolean deleteTeam(int id) {
        return repository.deleteTeam(id);
    }
}
