package com.example.sm.service;

import com.example.sm.dao.ManagerRepository;
import com.example.sm.dao.ProfessorRepository;
import com.example.sm.dao.StudentRepository;
import com.example.sm.model.User;
import com.example.sm.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private ManagerRepository managerRepository;
    private ProfessorRepository professorRepository;
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user;

        user = managerRepository.findByUsername(userName);
        if (user == null) {

            user = professorRepository.findByUsername(userName);
            if (user == null) {

                user = studentRepository.findByUsername(userName);
                if (user == null) {

                    System.err.println("username not found");
                    throw new UsernameNotFoundException(userName + " not found.");
                }
            }
        }

        return new UserDetailsImpl(user);
    }
}
