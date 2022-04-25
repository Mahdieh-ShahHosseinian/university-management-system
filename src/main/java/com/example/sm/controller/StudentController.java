package com.example.sm.controller;

import com.example.sm.dto.StudentDTO;
import com.example.sm.service.crudservice.StudentCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.sm.controller.APIController.BASE_URI;

@RestController
@RequestMapping(BASE_URI + "/students")
public class StudentController implements ControllerInterface<StudentDTO> {

    private final StudentCRUDService studentCRUDService;

    public StudentController(StudentCRUDService studentCRUDService) {
        this.studentCRUDService = studentCRUDService;
    }

    @Override
    @PostMapping
    public StudentDTO save(@RequestBody StudentDTO studentDTO) {
        return studentCRUDService.save(studentDTO);
    }

    @Override
    @GetMapping("/all")
    public List<StudentDTO> getAll() {
        return studentCRUDService.getAll();
    }

    @Override
    @GetMapping("/all/pagination")
    public List<StudentDTO> getAllPaginated(@RequestParam int page, @RequestParam int size) {
        return studentCRUDService.getAllPaginated(page, size);
    }

    @Override
    @GetMapping("/{id}")
    public StudentDTO get(@PathVariable int id) {
        return studentCRUDService.get(id);
    }

    @Override
    @PutMapping("{id}")
    public StudentDTO update(@RequestBody StudentDTO studentDTO, @PathVariable("id") int id) {
        return studentCRUDService.update(studentDTO, id);
    }

    @Override
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id) {
        studentCRUDService.delete(id);
    }

    @PutMapping("{id}/addCourse")
    public void addCourse(@PathVariable("id") int id, @RequestParam("professorId") int professorId, @RequestParam("courseId") int courseId) {
        studentCRUDService.addCourse(id, professorId, courseId);
    }

    @GetMapping("{id}/average")
    public Double getAverage(@PathVariable("id") int id) {
        return studentCRUDService.getAverage(id);
    }
}