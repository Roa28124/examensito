package com.examen.crud.service;

import com.examen.crud.model.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private List<Team>teams=new ArrayList<>();
    private Long nextId=1L;
    public List<Team>getTeams(){
        return teams;
    }
    public Optional<Team>getUserById(Long id){
        return teams.stream().filter(team -> team.getId().equals(id) ).findFirst();
    }
    public Team createTeam (Team team){
        team.setId(nextId++);
        teams.add(team);
        return team;
    }
    public Optional<Team> updateTeam(Long id, Team team){
        Optional<Team>teamOptional=getUserById(id);
        teamOptional.ifPresent(u ->{
            u.setName(team.getName());
            u.setEmail(team.getEmail());
        });
        return teamOptional;
    }
    public boolean deleteteam (Long id){
        return teams.removeIf(team -> team.getId().equals(id));
    }
}
