package com.example.sm.controller;

import com.example.sm.dto.CourseDTO;
import com.example.sm.dto.ProfessorDTO;
import com.example.sm.dto.StudentDTO;
import com.example.sm.service.crudservice.ProfessorCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.example.sm.controller.APIController.BASE_URI;

@RestController
@RequestMapping(BASE_URI + "/professors")
public class ProfessorController implements ControllerInterface<ProfessorDTO> {

    private final ProfessorCRUDService professorCRUDService;

    public ProfessorController(ProfessorCRUDService professorCRUDService) {
        this.professorCRUDService = professorCRUDService;
    }

    @Override
    @PostMapping
    public ProfessorDTO save(@RequestBody ProfessorDTO professorDTO) {
        return professorCRUDService.save(professorDTO);
    }

    @Override
    @GetMapping("/all")
    public List<ProfessorDTO> getAll() {
        return professorCRUDService.getAll();
    }

    @Override
    @GetMapping("/all/pagination")
    public List<ProfessorDTO> getAllPaginated(@RequestParam int page, @RequestParam int size) {
        return professorCRUDService.getAllPaginated(page, size);
    }

    @Override
    @GetMapping("/{id}")
    public ProfessorDTO get(@PathVariable int id) {
        return professorCRUDService.get(id);
    }

    @Override
    @PutMapping("/{id}")
    public ProfessorDTO update(@RequestBody ProfessorDTO professorDTO, @PathVariable("id") int id) {
        return professorCRUDService.update(professorDTO, id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        professorCRUDService.delete(id);
    }

    @PutMapping("{id}/addCourse")
    public void addCourse(@PathVariable("id") int id, @RequestParam("courseId") int cId) {
        professorCRUDService.addCourse(id, cId);
    }

    @GetMapping("/{id}/courses")
    public Set<CourseDTO> getCourses(@PathVariable("id") int id) {
        return professorCRUDService.getCourses(id);
    }

    @GetMapping("/{id}/students")
    public Set<StudentDTO> getStudents(@PathVariable("id") int id) {
        return professorCRUDService.getStudents(id);
    }

    @PutMapping("/{id}/studentGrade")
    public void setGrade(@PathVariable("id") int id, @RequestParam("sId") int studentId, @RequestParam("cId") int courseId, @RequestParam double grade) {
        professorCRUDService.setGrade(id, studentId, courseId, grade);
    }

    @GetMapping("/{id}/studentsAverage")
    public double getAverage(@PathVariable("id") int id) {
        return professorCRUDService.getAverage(id);
    }
}
