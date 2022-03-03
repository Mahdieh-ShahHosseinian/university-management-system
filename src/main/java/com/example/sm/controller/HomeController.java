package com.example.sm.controller;

import org.springframework.hateoas.Link;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.sm.controller.APIController.BASE_URI;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(BASE_URI)
public class HomeController extends APIController {

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_PROFESSOR', 'ROLE_STUDENT')")
    public List<Link> showLinks() {

        Link l1 = linkTo(FacultyController.class).slash("/all").withRel("faculties");
        Link l2 = linkTo(ProfessorController.class).slash("/all").withRel("professors");
        Link l3 = linkTo(StudentController.class).slash("/all").withRel("students");

        return List.of(l1, l2, l3);
    }
}

