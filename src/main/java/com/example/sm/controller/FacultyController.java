package com.example.sm.controller;

import com.example.sm.dto.FacultyDTO;
import com.example.sm.exception.RecordNotFoundException;
import com.example.sm.model.Course;
import com.example.sm.model.Faculty;
import com.example.sm.model.Professor;
import com.example.sm.model.Student;
import com.example.sm.service.CourseService;
import com.example.sm.service.FacultyService;
import com.example.sm.service.ProfessorService;
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
@RequestMapping("/faculties")
public class FacultyController implements ControllerInterface<Faculty, FacultyDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FacultyService facultyService;


    @Override
    @PostMapping
    public Faculty save(@Valid @RequestBody FacultyDTO facultyDto) {

        Faculty faculty = modelMapper.map(facultyDto, Faculty.class);
        faculty = facultyService.save(faculty);
        addPrimaryLinks(faculty);
        addOptionalLinks(faculty);
        return faculty;
    }

    @Override
    @GetMapping("/getAll")
    public List<Faculty> getAll() {

        List<Faculty> faculties = facultyService.getAll();
        for (Faculty faculty : faculties) {
            addPrimaryLinks(faculty);
            addOptionalLinks(faculty);
        }
        return faculties;
    }

    @Override
    @GetMapping("/{id}")
    public Faculty get(@PathVariable("id") int id) {

        Faculty faculty = facultyService.get(id);
        addPrimaryLinks(faculty);
        addOptionalLinks(faculty);
        return faculty;
    }

    @Override
    @PutMapping("/{id}")
    public Faculty update(@Valid @RequestBody FacultyDTO facultyDto, @PathVariable("id") int id) {

        Faculty faculty = modelMapper.map(facultyDto, Faculty.class);
        if (facultyService.get(id) == null) {
            throw new RecordNotFoundException("Invalid faculty id : " + id);
        }
        faculty = facultyService.update(faculty, id);
        addPrimaryLinks(faculty);
        addOptionalLinks(faculty);
        return faculty;
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {

        if (facultyService.get(id) == null) {
            throw new RecordNotFoundException("Invalid faculty id : " + id);
        }
        facultyService.delete(id);
    }

    private void addPrimaryLinks(Faculty faculty) {

        Link link = linkTo(FacultyController.class).slash(faculty.getId()).withSelfRel();
        faculty.add(link);
    }

    private void addOptionalLinks(Faculty faculty) {

        Link link = linkTo(methodOn(FacultyController.class).getCourses(faculty.getId())).withRel("faculty-courses");
        faculty.add(link);
        link = linkTo(methodOn(FacultyController.class).getProfessors(faculty.getId())).withRel("faculty-professors");
        faculty.add(link);
        link = linkTo(methodOn(FacultyController.class).getStudents(faculty.getId())).withRel("faculty-students");
        faculty.add(link);
    }

    @GetMapping("/{id}/courses")
    public Set<Course> getCourses(@PathVariable("id") int id) {

        Faculty faculty = facultyService.get(id);
        if (faculty == null) {
            throw new RecordNotFoundException("Invalid faculty id : " + id);
        }

        Set<Course> courses = faculty.getCourses();
        for (Course course : courses) {
            Link link = linkTo(CourseController.class).slash(course.getId()).withSelfRel();
            course.add(link);
            link = linkTo(FacultyController.class).slash(course.getFaculty().getId()).withSelfRel();
            if (!course.getFaculty().hasLinks()) course.getFaculty().add(link);
            link = linkTo(ProfessorController.class).slash(course.getProfessor().getPersonnelId()).withSelfRel();
            if (!course.getProfessor().hasLinks()) course.getProfessor().add(link);
            link = linkTo(FacultyController.class).slash(course.getProfessor().getFaculty().getId()).withSelfRel();
            if (!course.getProfessor().getFaculty().hasLinks()) course.getProfessor().getFaculty().add(link);
        }
        return courses;
    }

    @GetMapping("/{id}/professors")
    public Set<Professor> getProfessors(@PathVariable("id") int id) {

        Faculty faculty = facultyService.get(id);
        if (faculty == null) {
            throw new RecordNotFoundException("Invalid faculty id : " + id);
        }

        Set<Professor> professors = faculty.getProfessors();
        for (Professor professor : professors) {

            Link link = linkTo(ProfessorController.class).slash(professor.getPersonnelId()).withSelfRel();
            professor.add(link);
            link = linkTo(FacultyController.class).slash(professor.getFaculty().getId()).withSelfRel();
            if (!professor.getFaculty().hasLinks()) professor.getFaculty().add(link);
        }
        return professors;
    }

    @GetMapping("/{id}/students")
    public Set<Student> getStudents(@PathVariable("id") int id) {

        Faculty faculty = facultyService.get(id);
        if (faculty == null) {
            throw new RecordNotFoundException("Invalid faculty id : " + id);
        }

        Set<Student> students = faculty.getStudents();
        for (Student student : students) {

            Link link = linkTo(StudentController.class).slash(student.getStudentId()).withSelfRel();
            student.add(link);

            link = linkTo(FacultyController.class).slash(student.getFaculty().getId()).withSelfRel();
            if (!student.getFaculty().hasLinks()) student.getFaculty().add(link);
        }
        return students;
    }
}
