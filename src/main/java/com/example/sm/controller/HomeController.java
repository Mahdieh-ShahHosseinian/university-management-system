package com.example.sm.controller;

import org.springframework.hateoas.Link;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_PROFESSOR', 'ROLE_STUDENT')")
    public List<Link> showLinks() {

        Link l1 = linkTo(FacultyController.class).slash("/getAll").withRel("faculties");
        Link l2 = linkTo(ProfessorController.class).slash("/getAll").withRel("professors");
        Link l3 = linkTo(StudentController.class).slash("/getAll").withRel("students");

        return List.of(l1, l2, l3);
    }
}

