package com.example.sm.controller;

import org.springframework.stereotype.Component;

// Couldn't use enum-type instead :/
@Component("Roles")
public class Roles {

    public static final String MANAGER = "MANAGER";
    public static final String PROFESSOR = "PROFESSOR";
    public static final String STUDENT = "STUDENT";
}
