package com.example.sm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "studentCourses", "role", "password"})
public class Student extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private int studentId;

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
    @OneToMany(mappedBy = "studentCourseId.student", fetch = FetchType.EAGER)
    private Set<StudentCourse> studentCourses;

    @Getter
    @Setter
    @ManyToOne
    private Faculty faculty;


    public Student() {
        role = "STUDENT";
    }

    @Override
    public boolean equals(Object obj) {

        Student student = null;
        if (obj instanceof Student) student = (Student) obj;
        assert student != null;
        return studentId == student.getStudentId();
    }
}
