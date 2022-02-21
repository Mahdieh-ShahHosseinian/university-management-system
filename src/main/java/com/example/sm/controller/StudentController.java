package com.example.sm.controller;

import com.example.sm.dto.StudentDTO;
import com.example.sm.exception.RecordNotFoundException;
import com.example.sm.model.Course;
import com.example.sm.model.Student;
import com.example.sm.service.CourseService;
import com.example.sm.service.StudentCourseService;
import com.example.sm.service.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Link;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
public class StudentController implements ControllerInterface<Student, StudentDTO> {

    private ModelMapper modelMapper;
    private StudentService studentService;
    private CourseService courseService;
    private StudentCourseService studentCourseService;

    @Override
    @PostMapping
    @PreAuthorize("hasAnyAuthority('student:write')")
    public Student save(@Valid @RequestBody StudentDTO studentDto) {

        Student student = modelMapper.map(studentDto, Student.class);
        student = studentService.save(student);
        addPrimaryLinks(student);
        addOptionalLinks(student);
        return student;
    }

    @Override
    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public List<Student> getAll() {

        List<Student> students = studentService.getAll();
        for (Student student : students) {
            addPrimaryLinks(student);
            addOptionalLinks(student);
        }
        return students;
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('student:read') and @UserSecurity.hasUserId(authentication, #id)")
    public Student get(@PathVariable int id) {

        Student student = studentService.get(id);
        addPrimaryLinks(student);
        addOptionalLinks(student);
        return student;
    }

    @Override
    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('student:write') and @UserSecurity.hasUserId(authentication, #id)")
    public Student update(@Valid @RequestBody StudentDTO studentDto, @PathVariable("id") int id) {

        Student student = modelMapper.map(studentDto, Student.class);
        if (studentService.get(id) == null) {
            throw new RecordNotFoundException("Invalid student id : " + id);
        }
        student = studentService.update(student, id);
        addPrimaryLinks(student);
        addOptionalLinks(student);
        return student;
    }

    @Override
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void delete(@PathVariable("id") int id) {
        studentService.delete(id);
    }

    private void addPrimaryLinks(Student student) {

        Link link = linkTo(StudentController.class).slash(student.getStudentId()).withSelfRel();
        student.add(link);
    }

    private void addOptionalLinks(Student student) {

        Link link = linkTo(StudentController.class).slash((student.getStudentId())).slash("takeCourse?cId=").withRel("student-takeCourse");
        student.add(link);
        link = linkTo(StudentController.class).slash((student.getStudentId())).slash("getAverage").withRel("student-getAverage");
        student.add(link);
    }

    @PutMapping("/{id}/takeCourse")
    @PreAuthorize("hasAnyAuthority('course:write') and @UserSecurity.hasUserId(authentication, #id)")
    public Course selectCourse(@PathVariable("id") int id, @RequestParam("cId") int courseId) {

        if (courseService.get(courseId) == null) {
            throw new RecordNotFoundException("Invalid course id : " + courseId);
        }
        return studentCourseService.addCourse(studentService.get(id), courseService.get(courseId));
    }

    @GetMapping("/{id}/getAverage")
    @PreAuthorize("hasAnyAuthority('student:read') and @UserSecurity.hasUserId(authentication, #id)")
    public double getAverage(@PathVariable("id") int id) {
        return studentService.calculateAverage(id);
    }
}