package com.example.sm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "studentCourses"})
public class Course extends RepresentationModel<Course> {

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
    @Column(nullable = false, length = 1)
    private int unit;

    @Getter
    @Setter
    @ManyToOne
    private Professor professor;

    @Getter
    @Setter
    @OneToMany(mappedBy = "studentCourseId.course", fetch = FetchType.EAGER)
    private Set<StudentCourse> studentCourses;

    @Getter
    @Setter
    @ManyToOne
    private Faculty faculty;

    @Override
    public boolean equals(Object obj) {

        Course course = null;
        if (obj instanceof Course) course = (Course) obj;
        assert course != null;
        return id == course.getId();
    }
}
