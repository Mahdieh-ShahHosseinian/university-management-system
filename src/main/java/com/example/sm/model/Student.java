package com.example.sm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

import static com.example.sm.security.ApplicationUserRole.PROFESSOR;
import static com.example.sm.security.ApplicationUserRole.STUDENT;

@Entity
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "studentCourses", "role", "password", "profilePicture"})
public class Student extends ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int studentId;

    @Column(unique = true, nullable = false, length = 10)
    private long nationalId;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @OneToMany(mappedBy = "studentCourseId.student", fetch = FetchType.EAGER)
    private Set<StudentCourse> studentCourses;

    @ManyToOne
    private Faculty faculty;

    @Lob
    @Column(length = 1000)
    private byte[] profilePicture;

    public Student() {
        setGrantedAuthorities(STUDENT.getGrantedAuthorities());
    }

    @Override
    public boolean equals(Object obj) {

        Student student = null;
        if (obj instanceof Student) student = (Student) obj;
        assert student != null;
        return studentId == student.getStudentId();
    }
}