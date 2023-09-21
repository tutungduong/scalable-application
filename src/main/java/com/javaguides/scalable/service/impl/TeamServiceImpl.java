package com.javaguides.scalable.service.impl;

import com.javaguides.scalable.entity.Team;
import com.javaguides.scalable.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl  implements TeamService {

    @Override
    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<Team>();
        teams.add(new Team(1, "Initiates"));
        teams.add(new Team(2, "Knights"));
        teams.add(new Team(3, "Padawans"));
        teams.add(new Team(4, "Rookies"));
        teams.add(new Team(5, "Wizards"));
        return teams;
    }
}
