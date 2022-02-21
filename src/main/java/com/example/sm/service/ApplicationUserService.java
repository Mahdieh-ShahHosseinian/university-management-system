package com.example.sm.service;

import com.example.sm.dao.ManagerRepository;
import com.example.sm.dao.ProfessorRepository;
import com.example.sm.dao.StudentRepository;
import com.example.sm.model.ApplicationUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicationUserService implements UserDetailsService {

    private ManagerRepository managerRepository;
    private ProfessorRepository professorRepository;
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        ApplicationUser applicationUser;

        applicationUser = managerRepository.findByUsername(userName);
        if (applicationUser == null) {

            applicationUser = professorRepository.findByUsername(userName);
            if (applicationUser == null) {

                applicationUser = studentRepository.findByUsername(userName);
                if (applicationUser == null) {

                    System.err.println("username not found");
                    throw new UsernameNotFoundException(userName + " not found.");
                }
            }
        }

        return applicationUser;
    }
}
