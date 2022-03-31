package com.example.sm.controller;

import com.example.sm.dto.FacultyDTO;
import com.example.sm.service.crudservice.FacultyCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.sm.controller.APIController.BASE_URI;

@RestController
@RequestMapping(BASE_URI + "faculties")
public class FacultyController implements ControllerInterface<FacultyDTO> {

    private final FacultyCRUDService facultyCRUDService;

    public FacultyController(FacultyCRUDService facultyCRUDService) {
        this.facultyCRUDService = facultyCRUDService;
    }

    @Override
    @PostMapping
    public FacultyDTO save(@RequestBody FacultyDTO facultyDTO) {
        return facultyCRUDService.save(facultyDTO);
    }

    @Override
    @GetMapping("/all")
    public List<FacultyDTO> getAll() {
        return facultyCRUDService.getAll();
    }

    @Override
    @GetMapping("/all/pagination")
    public List<FacultyDTO> getAllPaginated(@RequestParam int page, @RequestParam int size) {
        return facultyCRUDService.getAllPaginated(page, size);
    }

    @Override
    @GetMapping("/{id}")
    public FacultyDTO get(@PathVariable("id") int id) {
        return facultyCRUDService.get(id);
    }

    @Override
    @PutMapping("/{id}")
    public FacultyDTO update(@RequestBody FacultyDTO facultyDTO, @PathVariable("id") int id) {
        return facultyCRUDService.update(facultyDTO, id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        facultyCRUDService.delete(id);
    }
}
