package com.example.sm.controller;

import com.example.sm.dto.ProfessorDTO;
import com.example.sm.exception.RecordNotFoundException;
import com.example.sm.model.Course;
import com.example.sm.model.Professor;
import com.example.sm.model.Student;
import com.example.sm.model.StudentCourse;
import com.example.sm.service.CourseService;
import com.example.sm.service.ProfessorService;
import com.example.sm.service.StudentCourseService;
import com.example.sm.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/professors")
public class ProfessorController implements ControllerInterface<Professor, ProfessorDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentCourseService studentCourseService;


    @Override
    @PostMapping
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
    public Professor get(@PathVariable int id) {

        Professor professor = professorService.get(id);
        addPrimaryLinks(professor);
        addOptionalLinks(professor);
        return professor;
    }

    @Override
    @PutMapping("/{id}")
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
        link = linkTo(ProfessorController.class).slash((professor.getPersonnelId())).slash("updateStudentGrade").withRel("professor-updateStudentGrade");
        professor.add(link);
        link = linkTo(ProfessorController.class).slash((professor.getPersonnelId())).slash("studentsAverage").withRel("professor-studentsAverage");
        professor.add(link);
    }

    @GetMapping("/{id}/courses")
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
    public double getAverage(@PathVariable("id") int id) {

        Professor professor = professorService.get(id);
        if (professor == null) {
            throw new RecordNotFoundException("Invalid Professor personnel ID : " + id);
        }
        return professorService.calculateAllStudentsAverage(getStudents(id));
    }
}
