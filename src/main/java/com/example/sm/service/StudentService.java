package com.example.sm.service;

import com.example.sm.dao.StudentRepository;
import com.example.sm.model.Faculty;
import com.example.sm.model.Student;
import com.example.sm.model.StudentCourse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class StudentService implements ServiceInterface<Student> {

    private StudentRepository repository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Student save(Student student) {

        String encodedPass = bCryptPasswordEncoder.encode(student.getPassword());
        student.setPassword(encodedPass);
        return repository.save(student);
    }

    @Override
    public List<Student> getAll() {
        return repository.findAll();
    }

    @Override
    public Student get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Student update(Student student, int id) {

        Student s = get(id);
        s.setNationalId(student.getNationalId());
        s.setFirstName(student.getFirstName());
        s.setLastName(student.getLastName());
        s.setFaculty(student.getFaculty());
        return save(s);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    public Set<Student> findByFaculty(Faculty faculty) {
        return repository.findByFaculty(faculty);
    }

    public double calculateAverage(int id) {

        Student student = get(id);
        double totalAverage = 0;
        int totalUnits = 0;
        for (StudentCourse sc : student.getStudentCourses()) {

            totalAverage += sc.getGrade() * sc.getStudentCourseId().getCourse().getUnit();
            totalUnits += sc.getStudentCourseId().getCourse().getUnit();
        }
        return totalAverage / totalUnits;
    }
}
