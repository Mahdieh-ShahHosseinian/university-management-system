package com.example.sm.security;

import com.example.sm.controller.Roles;
import com.example.sm.model.User;
import com.example.sm.service.ManagerService;
import com.example.sm.service.ProfessorService;
import com.example.sm.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component("UserSecurity")
@AllArgsConstructor
public class UserSecurity {

    private ManagerService managerService;
    private ProfessorService professorService;
    private StudentService studentService;

    public boolean hasUserId(Authentication authentication, int id) {

        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()).get(0);

        User user = null;
        switch (role) {
            case Roles.MANAGER:
                user = managerService.get(id);
                break;
            case Roles.PROFESSOR:
                user = professorService.get(id);
                break;
            case Roles.STUDENT:
                user = studentService.get(id);
                break;
        }

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        assert user != null;
        return user.getUsername().equals(userPrincipal.getUsername());
    }
}
