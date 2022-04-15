package com.example.sm.service.coreservice;

import com.example.sm.dao.StudentRepository;
import com.example.sm.model.Student;
import com.example.sm.model.StudentCourse;
import com.example.sm.service.ServiceInterface;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCoreService implements ServiceInterface<Student, Integer> {

    private final StudentRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final StudentCourseCoreService studentCourseCoreService;

    public StudentCoreService(StudentRepository repository, PasswordEncoder passwordEncoder, StudentCourseCoreService studentCourseCoreService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.studentCourseCoreService = studentCourseCoreService;
    }

    @Override
    public Student save(Student student) {

        String encodedPass = passwordEncoder.encode(student.getPassword());
        student.setPassword(encodedPass);
        return repository.save(student);
    }

    @Override
    public List<Student> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Student> getAllPaginated(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).toList();
    }

    @Override
    public Student get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Student update(Student student, Integer id) {

        Student s = get(id);
        s.setUsername(student.getUsername());
        s.setPassword(student.getPassword());
        s.setNationalId(student.getNationalId());
        s.setFirstname(student.getFirstname());
        s.setLastname(student.getLastname());
        s.setFaculty(student.getFaculty());
        return save(s);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public void addCourse(Integer id, Integer professorId, Integer courseId) {
        studentCourseCoreService.save(id, professorId, courseId);
    }

    public Double getAverage(Integer id) {

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