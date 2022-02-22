package com.example.sm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

import static com.example.sm.security.ApplicationUserRole.PROFESSOR;

@Entity
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "courses", "role", "password"})
public class Professor extends ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int personnelId;

    @Column(unique = true, nullable = false, length = 10)
    private long nationalId;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)
    private Set<Course> courses;

    @ManyToOne
    private Faculty faculty;

    public Professor() {
        setGrantedAuthorities(PROFESSOR.getGrantedAuthorities());
    }

    @Override
    public boolean equals(Object obj) {

        Professor professor = null;
        if (obj instanceof Professor) professor = (Professor) obj;
        assert professor != null;
        return personnelId == professor.getPersonnelId();
    }
}
