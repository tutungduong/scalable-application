package com.javaguides.scalable.service;

import com.javaguides.scalable.entity.Team;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();
}
