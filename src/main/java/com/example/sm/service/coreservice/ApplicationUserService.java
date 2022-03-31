package com.example.sm.service.coreservice;

import com.example.sm.dao.AdminRepository;
import com.example.sm.dao.ManagerRepository;
import com.example.sm.dao.ProfessorRepository;
import com.example.sm.dao.StudentRepository;
import com.example.sm.model.ApplicationUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final ManagerRepository managerRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;

    public ApplicationUserService(AdminRepository adminRepository, ManagerRepository managerRepository, ProfessorRepository professorRepository, StudentRepository studentRepository) {
        this.adminRepository = adminRepository;
        this.managerRepository = managerRepository;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        ApplicationUser applicationUser;

        applicationUser = adminRepository.findByUsername(userName);
        if (applicationUser == null) {

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
        }

        return applicationUser;
    }
}