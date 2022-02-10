package com.example.sm.controller;

import com.example.sm.dto.CourseDTO;
import com.example.sm.exception.RecordNotFoundException;
import com.example.sm.model.Course;
import com.example.sm.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/courses")
public class CourseController implements ControllerInterface<Course, CourseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CourseService courseService;


    @Override
    @PostMapping
    public Course save(@Valid @RequestBody CourseDTO courseDto) {

        Course course = modelMapper.map(courseDto, Course.class);
        course = courseService.save(course);
        Link link = linkTo(CourseController.class).slash(course.getId()).withSelfRel();
        course.add(link);
        return course;
    }

    @Override
    @GetMapping("/getAll")
    public List<Course> getAll() {

        List<Course> courses = courseService.getAll();
        for (Course course : courses) {
            addPrimaryLinks(course);
        }
        return courses;
    }

    @Override
    @GetMapping("/{id}")
    public Course get(@PathVariable int id) {

        Course course = courseService.get(id);
        addPrimaryLinks(course);
        return course;
    }

    @Override
    @PutMapping("{id}")
    public Course update(@Valid @RequestBody CourseDTO courseDto, @PathVariable int id) {

        Course course = modelMapper.map(courseDto, Course.class);
        if (courseService.get(id) == null) {
            throw new RecordNotFoundException("Invalid course id : " + id);
        }
        course = courseService.update(course, id);
        addPrimaryLinks(course);
        return course;
    }

    @Override
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id) {

        if (courseService.get(id) == null) {
            throw new RecordNotFoundException("Invalid course id : " + id);
        }
        courseService.delete(id);
    }

    private void addPrimaryLinks(Course course) {

        Link link = linkTo(CourseController.class).slash(course.getId()).withSelfRel();
        course.add(link);
        link = linkTo(FacultyController.class).slash(course.getFaculty().getId()).withSelfRel();
        if (!course.getFaculty().hasLinks()) course.getFaculty().add(link);
        link = linkTo(ProfessorController.class).slash(course.getProfessor().getPersonnelId()).withSelfRel();
        course.getProfessor().add(link);
        link = linkTo(FacultyController.class).slash(course.getProfessor().getFaculty().getId()).withSelfRel();
        if (!course.getProfessor().getFaculty().hasLinks()) course.getProfessor().getFaculty().add(link);
    }
}