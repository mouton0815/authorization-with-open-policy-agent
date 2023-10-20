package com.example.demo.rest;

import com.example.demo.domain.Team;
import com.example.demo.domain.TeamData;
import com.example.demo.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class TeamController {

    private final TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    @PostMapping(value = "/teams", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Team> postTeam(@RequestBody TeamData teamData) {
        Team team = service.addTeam(teamData);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(team.id).toUri();
        return ResponseEntity.created(location).body(team);
    }

    @GetMapping(value = "/teams", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Team> getTeams() {
        return service.getTeams();
    }

    @GetMapping(value = "/teams/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Team getTeam (@PathVariable("id") int id) {
        return service.getTeam(id).orElseThrow(TeamNotFoundException::new);
    }

    @PutMapping(value = "/teams/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Team putTeam(@PathVariable("id") int id, @RequestBody TeamData teamData) {
        return service.updateTeam(id, teamData).orElseThrow(TeamNotFoundException::new);
    }

    @DeleteMapping("/teams/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@PathVariable("id") int id) {
        if (!service.deleteTeam(id)) {
            throw new TeamNotFoundException();
        }
    }

    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such team")
    public static class TeamNotFoundException extends RuntimeException {
        // Empty
    }
}
