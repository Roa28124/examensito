package com.examen.crud.controller;

import com.examen.crud.model.Team;
import com.examen.crud.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/team")
public class TeamController {
    private final TeamService teamService;
    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

   @GetMapping
    public List<Team> getAllUsers(){
        return teamService.getTeams();
   }
   @GetMapping("/{id}")
    public ResponseEntity<Team>getTeamById(@PathVariable Long id){
       Optional<Team>team=teamService.getUserById(id);
       return  team.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
   }
   @PostMapping
    public Team createTeam (@RequestBody Team team){
        return teamService.createTeam(team);
   }
    @PutMapping("/{id}")
    public ResponseEntity<Team>updateTeam(@PathVariable Long id, @RequestBody Team teamDetails ){
        Optional<Team>team=teamService.updateTeam(id,teamDetails);
        return team.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam (@PathVariable Long id ){
    if (teamService.deleteteam(id)){
        return ResponseEntity.ok().build();
    }else {
        return ResponseEntity.notFound().build();
    }
    }
}
