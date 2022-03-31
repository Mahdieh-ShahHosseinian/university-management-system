package com.example.sm.controller;

import com.example.sm.dto.CourseDTO;
import com.example.sm.service.crudservice.CourseCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.sm.controller.APIController.BASE_URI;

@RestController
@RequestMapping(BASE_URI + "courses")
public class CourseController implements ControllerInterface<CourseDTO> {

    private final CourseCRUDService courseCRUDService;

    public CourseController(CourseCRUDService courseCRUDService) {
        this.courseCRUDService = courseCRUDService;
    }

    @Override
    @PostMapping
    public CourseDTO save(@RequestBody CourseDTO courseDTO) {
        return courseCRUDService.save(courseDTO);
    }

    @Override
    @GetMapping("/all")
    public List<CourseDTO> getAll() {
        return courseCRUDService.getAll();
    }

    @Override
    @GetMapping("/all/pagination")
    public List<CourseDTO> getAllPaginated(@RequestParam int page, @RequestParam int size) {
        return courseCRUDService.getAllPaginated(page, size);
    }

    @Override
    @GetMapping("/{id}")
    public CourseDTO get(@PathVariable int id) {
        return courseCRUDService.get(id);
    }

    @Override
    @PutMapping("{id}")
    public CourseDTO update(@RequestBody CourseDTO courseDTO, @PathVariable int id) {
        return courseCRUDService.update(courseDTO, id);
    }

    @Override
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id) {
        courseCRUDService.delete(id);
    }
}
