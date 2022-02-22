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
        
        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        String role = "";
        for (String authority : authorities) {
            if (authority.startsWith("ROLE_")) {
                role = authority;
                break;
            }
        }

        ApplicationUser applicationUser = null;
        switch (role) {
            case "ROLE_MANAGER":
                applicationUser = managerService.get(id);
                break;
            case "ROLE_PROFESSOR":
                applicationUser = professorService.get(id);
                break;
            case "ROLE_STUDENT":
                applicationUser = studentService.get(id);
                break;
        }

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        assert applicationUser != null;
        return applicationUser.getUsername().equals(userPrincipal.getUsername());
    }
}
