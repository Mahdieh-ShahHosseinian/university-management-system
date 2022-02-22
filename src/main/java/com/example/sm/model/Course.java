package com.example.sm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "studentCourses"})
public class Course extends RepresentationModel<Course> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 1)
    private int unit;

    @ManyToOne
    private Professor professor;

    @OneToMany(mappedBy = "studentCourseId.course", fetch = FetchType.EAGER)
    private Set<StudentCourse> studentCourses;

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
