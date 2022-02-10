package com.example.sm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sm.dao.StudentCourseRepository;
import com.example.sm.model.Course;
import com.example.sm.model.Student;
import com.example.sm.model.StudentCourse;
import com.example.sm.model.StudentCourseId;

@Service
public class StudentCourseService {

    @Autowired
    private StudentCourseRepository repository;


    public StudentCourse save(Student student, Course course) {

        StudentCourse studentCourse = new StudentCourse(student, course);
        return repository.save(studentCourse);
    }

    private StudentCourse save(StudentCourse studentCourse) {
        return repository.save(studentCourse);
    }

    public List<StudentCourse> getAll() {
        return repository.findAll();
    }

    public StudentCourse get(Student student, Course course) {

        StudentCourseId studentCourseId = new StudentCourseId(student, course);
        return repository.findByStudentCourseId(studentCourseId);
    }

    public StudentCourse update(Course course, Student student, double grade) {

        StudentCourseId studentCourseId = new StudentCourseId(student, course);
        StudentCourse studentCourse = repository.findByStudentCourseId(studentCourseId);
        studentCourse.setGrade(grade);
        return save(studentCourse);
    }

    public void delete(Student student, Course course) {

        StudentCourseId studentCourseId = new StudentCourseId(student, course);
        repository.deleteByStudentCourseId(studentCourseId);
    }

    public Course addCourse(Student student, Course course) {

        StudentCourse studentCourse = new StudentCourse(student, course);
        return repository.save(studentCourse).getCourse();
    }
}
