package com.example.sm.controller;

import com.example.sm.dto.ProfessorDTO;
import com.example.sm.exception.RecordNotFoundException;
import com.example.sm.model.*;
import com.example.sm.service.CourseService;
import com.example.sm.service.ProfessorService;
import com.example.sm.service.StudentCourseService;
import com.example.sm.service.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Link;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/professors")
@AllArgsConstructor
public class ProfessorController implements ControllerInterface<Professor, ProfessorDTO> {

    private ModelMapper modelMapper;
    private ProfessorService professorService;
    private StudentService studentService;
    private CourseService courseService;
    private StudentCourseService studentCourseService;

    @Override
    @PostMapping
    @PreAuthorize("hasAnyAuthority('professor:write')")
    public Professor save(@Valid @RequestBody ProfessorDTO professorDto) {

        Professor professor = modelMapper.map(professorDto, Professor.class);
        professor = professorService.save(professor);
        Link link = linkTo(ProfessorController.class).slash(professor.getPersonnelId()).withSelfRel();
        professor.add(link);
        addOptionalLinks(professor);
        return professor;
    }

    @Override
    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public List<Professor> getAll() {

        List<Professor> professors = professorService.getAll();
        for (Professor professor : professors) {
            addPrimaryLinks(professor);
            addOptionalLinks(professor);
        }
        return professors;
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasAnyAuthority('professor:read') and @UserSecurity.hasUserId(authentication, #id)")
    public Professor get(@PathVariable int id) {

        Professor professor = professorService.get(id);
        addPrimaryLinks(professor);
        addOptionalLinks(professor);
        return professor;
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasAnyAuthority('professor:write') and @UserSecurity.hasUserId(authentication, #id)")
    public Professor update(@Valid @RequestBody ProfessorDTO professorDto, @PathVariable("id") int id) {

        Professor professor = modelMapper.map(professorDto, Professor.class);
        if (professorService.get(id) == null) {
            throw new RecordNotFoundException("Invalid Professor personnel ID : " + id);
        }
        professor = professorService.update(professor, id);
        addPrimaryLinks(professor);
        addOptionalLinks(professor);
        return professor;
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('professor:write')")
    public void delete(@PathVariable("id") int id) {

        if (professorService.get(id) == null) {
            throw new RecordNotFoundException("Invalid Professor personnel ID : " + id);
        }
        professorService.delete(id);
    }

    private void addPrimaryLinks(Professor professor) {

        Link link = linkTo(ProfessorController.class).slash(professor.getPersonnelId()).withSelfRel();
        professor.add(link);
        link = linkTo(FacultyController.class).slash(professor.getFaculty().getId()).withSelfRel();
        // To prevent add duplicate links, as some professors can share same faculty
        if (!professor.getFaculty().hasLinks()) professor.getFaculty().add(link);
    }

    private void addOptionalLinks(Professor professor) {

        Link link = linkTo(methodOn(ProfessorController.class).getCourses(professor.getPersonnelId())).withRel("professor-courses");
        professor.add(link);
        link = linkTo(methodOn(ProfessorController.class).getStudents(professor.getPersonnelId())).withRel("professor-students");
        professor.add(link);
        link = linkTo(ProfessorController.class).slash((professor.getPersonnelId())).slash("updateStudentGrade?cId=&sId=&grade=").withRel("professor-updateStudentGrade");
        professor.add(link);
        link = linkTo(ProfessorController.class).slash((professor.getPersonnelId())).slash("studentsAverage").withRel("professor-studentsAverage");
        professor.add(link);
    }

    @GetMapping("/{id}/courses")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasAnyAuthority('professor:read') and @UserSecurity.hasUserId(authentication, #id)")
    public Set<Course> getCourses(@PathVariable("id") int id) {

        Professor professor = professorService.get(id);
        if (professor == null) {
            throw new RecordNotFoundException("Invalid Professor personnel ID : " + id);
        }

        Set<Course> courses = professor.getCourses();
        for (Course course : courses) {
            Link link = linkTo(CourseController.class).slash(course.getId()).withSelfRel();
            course.add(link);
            link = linkTo(FacultyController.class).slash(course.getFaculty().getId()).withSelfRel();
            if (!course.getFaculty().hasLinks()) course.getFaculty().add(link);
            link = linkTo(ProfessorController.class).slash(professor.getPersonnelId()).withSelfRel();
            if (!course.getProfessor().hasLinks()) course.getProfessor().add(link);
            link = linkTo(FacultyController.class).slash(professor.getFaculty().getId()).withSelfRel();
            if (!course.getProfessor().getFaculty().hasLinks()) course.getProfessor().getFaculty().add(link);
        }
        return courses;
    }

    @GetMapping("/{id}/students")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasAnyAuthority('professor:read') and @UserSecurity.hasUserId(authentication, #id)")
    public Set<Student> getStudents(@PathVariable("id") int id) {

        Professor professor = professorService.get(id);
        if (professor == null) {
            throw new RecordNotFoundException("Invalid Professor personnel ID : " + id);
        }
        Set<Student> students = professorService.getStudents(id);
        for (Student student : students) {
            Link link = linkTo(StudentController.class).slash(student.getStudentId()).withSelfRel();
            student.add(link);
            link = linkTo(FacultyController.class).slash(student.getFaculty().getId()).withSelfRel();
            if (!student.getFaculty().hasLinks()) student.getFaculty().add(link);
        }
        return students;
    }

    @PutMapping("/{id}/updateStudentGrade")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasAnyAuthority('course:write') and @UserSecurity.hasUserId(authentication, #id)")
    public StudentCourse setGrade(@PathVariable("id") int id, @RequestParam("cId") int courseId,
                                  @RequestParam("sId") int studentId, @RequestParam double grade) {

        Professor professor = professorService.get(id);
        if (professor == null) {
            throw new RecordNotFoundException("Invalid Professor personnel ID : " + id);
        } else if (courseService.get(courseId) == null) {
            throw new RecordNotFoundException("Invalid course id : " + courseId);
        } else if (!courseService.get(courseId).getProfessor().equals(professor)) {
            throw new RecordNotFoundException("This professor id [" + id + "] does not access to this course id : " + courseId);
        } else if (studentService.get(studentId) == null) {
            throw new RecordNotFoundException("Invalid student id : " + studentId);
        } else if (studentCourseService.get(studentService.get(studentId), courseService.get(courseId)) == null) {
            throw new RecordNotFoundException("Student id : " + studentId + " course id : " + courseId + " has not set yet.");
        }
        return studentCourseService.update(courseService.get(courseId), studentService.get(studentId), grade);
    }

    @GetMapping("/{id}/studentsAverage")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasAnyAuthority('professor:read') and @UserSecurity.hasUserId(authentication, #id)")
    public double getAverage(@PathVariable("id") int id) {

        Professor professor = professorService.get(id);
        if (professor == null) {
            throw new RecordNotFoundException("Invalid Professor personnel ID : " + id);
        }
        return professorService.calculateAllStudentsAverage(getStudents(id));
    }
}
