package com.example.sm.controller;

import com.example.sm.dto.ProfessorDTO;
import com.example.sm.service.crudservice.ProfessorCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.sm.controller.APIController.BASE_URI;

@RestController
@RequestMapping(BASE_URI + "professors")
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
}
