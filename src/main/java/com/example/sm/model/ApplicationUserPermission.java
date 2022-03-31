package com.example.sm.model;

public enum ApplicationUserPermission {

    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    MANAGER_READ("manager:read"),
    MANAGER_WRITE("manager:write"),
    PROFESSOR_READ("professor:read"),
    PROFESSOR_WRITE("professor:write"),
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    FACULTY_READ("faculty:read"),
    FACULTY_WRITE("faculty:write"),
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