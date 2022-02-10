package com.example.sm.service;

import com.example.sm.dao.ProfessorRepository;
import com.example.sm.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProfessorService implements ServiceInterface<Professor> {

    @Autowired
    private ProfessorRepository repository;


    @Override
    public Professor save(Professor professor) {
        return repository.save(professor);
    }

    @Override
    public List<Professor> getAll() {
        return repository.findAll();
    }

    @Override
    public Professor get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Professor update(Professor professor, int id) {

        Professor p = get(id);
        p.setNationalId(professor.getNationalId());
        p.setFirstName(professor.getFirstName());
        p.setLastName(professor.getLastName());
        p.setFaculty(professor.getFaculty());
        return save(p);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }


    public Set<Professor> findByFaculty(Faculty faculty) {
        return repository.findByFaculty(faculty);
    }

    public Set<Student> getStudents(int id) {

        Set<Student> students = new HashSet<>();
        Professor professor = get(id);
        Set<Course> courses = professor.getCourses();
        for (Course course : courses) {

            Set<StudentCourse> studentCourses = course.getStudentCourses();
            for (StudentCourse sc : studentCourses) {
                students.add(sc.getStudent());
            }
        }
        return students;
    }

    public double calculateAllStudentsAverage(Set<Student> students) {

        double totalAverage = 0;
        int totalUnits = 0;
        for (Student student : students) {
            for (StudentCourse sc : student.getStudentCourses()) {

                totalAverage += sc.getGrade() * sc.getCourse().getUnit();
                totalUnits += sc.getCourse().getUnit();
            }
        }

        return totalAverage / totalUnits;
    }
}
