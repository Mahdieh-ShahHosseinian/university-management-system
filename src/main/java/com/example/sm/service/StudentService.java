package com.example.sm.service;

import com.example.sm.dao.StudentRepository;
import com.example.sm.model.Faculty;
import com.example.sm.model.Professor;
import com.example.sm.model.Student;
import com.example.sm.model.StudentCourse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class StudentService implements ServiceInterface<Student> {

    private StudentRepository repository;
    private PasswordEncoder passwordEncoder;

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

    public void setProfilePicture(MultipartFile profilePic, int id) throws IOException {

        String picName = profilePic.getOriginalFilename();
        assert picName != null;
        String picFormat = picName.substring(picName.lastIndexOf('.'));
        if (picFormat.endsWith(".jpg") || picFormat.endsWith(".jpeg")
                || picFormat.endsWith(".png")) {
            Student s = get(id);
            s.setProfilePicture(profilePic.getBytes());
            save(s);
        } else {
            log.error("File format {} not supported.", picFormat);
            throw new IOException();
        }
    }
}
