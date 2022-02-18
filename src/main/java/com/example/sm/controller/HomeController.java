package com.example.sm.controller;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    @PreAuthorize(value = "permitAll()")
    public List<Link> showLinks() {

        Link l1 = Link.of("/faculties/getAll", LinkRelation.of("faculties"));
        Link l2 = Link.of("/professors/getAll", LinkRelation.of("professors"));
        Link l3 = Link.of("/students/getAll", LinkRelation.of("students"));

        return List.of(l1, l2, l3);
    }
}

