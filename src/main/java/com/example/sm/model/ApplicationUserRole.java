package com.example.sm.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.sm.model.ApplicationUserPermission.*;

public enum ApplicationUserRole {

    ADMIN(EnumSet.allOf(ApplicationUserPermission.class)),
    MANAGER(EnumSet.of(FACULTY_READ, FACULTY_WRITE, PROFESSOR_READ, PROFESSOR_WRITE, STUDENT_READ, STUDENT_WRITE, COURSE_READ, COURSE_WRITE)),
    PROFESSOR(EnumSet.of(PROFESSOR_READ, COURSE_WRITE)),
    STUDENT(EnumSet.of(STUDENT_READ, COURSE_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {

        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}