package com.example.sm.security;

public enum ApplicationUserPermission {

    FACULTY_READ("faculty:read"),
    FACULTY_WRITE("faculty:write"),
    PROFESSOR_READ("professor:read"),
    PROFESSOR_WRITE("professor:write"),
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
