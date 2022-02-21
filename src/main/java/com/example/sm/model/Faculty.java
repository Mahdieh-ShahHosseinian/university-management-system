package com.example.sm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "professors", "students", "courses"})
public class Faculty extends RepresentationModel<Faculty> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private int id;

    @Getter
    @Setter
    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @Getter
    @Setter
    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private Set<Professor> professors;

    @Getter
    @Setter
    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private Set<Student> students;

    @Getter
    @Setter
    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private Set<Course> courses;

    @Override
    public boolean equals(Object obj) {
        Faculty faculty = null;
        if (obj instanceof Faculty) faculty = (Faculty) obj;
        assert faculty != null;
        return id == faculty.getId();
    }
}