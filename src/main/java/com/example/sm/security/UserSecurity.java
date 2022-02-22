package com.example.sm.security;

import com.example.sm.controller.Roles;
import com.example.sm.model.ApplicationUser;
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

        ApplicationUser applicationUser = null;
        switch (role) {
            case "MANAGER":
                applicationUser = managerService.get(id);
                break;
            case "PROFESSOR":
                applicationUser = professorService.get(id);
                break;
            case "STUDENT":
                applicationUser = studentService.get(id);
                break;
        }

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        assert applicationUser != null;
        return applicationUser.getUsername().equals(userPrincipal.getUsername());
    }
}
