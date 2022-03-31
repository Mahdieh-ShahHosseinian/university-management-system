package com.example.sm.controller;

import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.sm.controller.APIController.BASE_URI;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(BASE_URI)
public class HomeController extends APIController {

    @GetMapping
    public List<Link> showLinks() {

        Link l1 = linkTo(FacultyController.class).slash("/all").withRel("faculties");
        Link l2 = linkTo(ProfessorController.class).slash("/all").withRel("professors");
        Link l3 = linkTo(StudentController.class).slash("/all").withRel("students");
        Link l4 = linkTo(CourseController.class).slash("/all").withRel("courses");

        return new ArrayList<>(Arrays.asList(l1, l2, l3, l4));
    }
}

