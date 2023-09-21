package com.javaguides.scalable.controller;

import com.javaguides.scalable.entity.Team;
import com.javaguides.scalable.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/teams")
public class TeamController {
    @GetMapping
    public String index(Model model) {
        return "teams/list";
    }
}