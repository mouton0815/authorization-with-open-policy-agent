package com.example.demo.repository;

import com.example.demo.domain.Team;
import com.example.demo.domain.TeamData;
import com.example.demo.service.TeamRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class TeamRepositoryImpl implements TeamRepository {
    private static final Map<Integer, TeamData> TEAMS = new HashMap<>();

    static {
        TEAMS.put(1, new TeamData("Team A", "Berlin"));
        TEAMS.put(2, new TeamData("Team B", "London"));
        TEAMS.put(3, new TeamData("Team C", "Paris"));
    }

    public Stream<Team> getTeams() {
        return TEAMS.entrySet().stream().map(e -> new Team(e.getKey(), e.getValue()));
    }

    public Optional<Team> getTeam(int id) {
        TeamData teamData = TEAMS.get(id);
        return teamData == null ? Optional.empty() : Optional.of(new Team(id, teamData));
    }

    public Team addTeam(TeamData team) {
        int id = TEAMS.size() + 1;
        TEAMS.put(id, team);
        return new Team(id, team);
    }

    public Optional<Team> updateTeam(int id, TeamData teamData) {
        if (!TEAMS.containsKey(id)) {
            return Optional.empty();
        }
        TEAMS.put(id, teamData);
        return Optional.of(new Team(id, teamData));
    }

    public boolean deleteTeam(int id) {
        return TEAMS.remove(id) != null;
    }
}
