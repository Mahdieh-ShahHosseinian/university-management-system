package com.example.sm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "courses", "role", "password"})
public class Professor extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private int personnelId;

    @Getter
    @Setter
    @Column(unique = true, nullable = false, length = 10)
    private long nationalId;

    @Getter
    @Setter
    @Column(nullable = false, length = 20)
    private String firstName;

    @Getter
    @Setter
    @Column(nullable = false, length = 20)
    private String lastName;

    @Getter
    @Setter
    @OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)
    private Set<Course> courses;

    @Getter
    @Setter
    @ManyToOne
    private Faculty faculty;


    public Professor() {
        role = "PROFESSOR";
    }

    @Override
    public boolean equals(Object obj) {

        Professor professor = null;
        if (obj instanceof Professor) professor = (Professor) obj;
        assert professor != null;
        return personnelId == professor.getPersonnelId();
    }
}
