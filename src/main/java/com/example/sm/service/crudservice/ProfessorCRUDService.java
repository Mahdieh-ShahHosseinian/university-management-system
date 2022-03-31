package com.example.sm.service.crudservice;

import com.example.sm.dto.ProfessorDTO;
import com.example.sm.model.Professor;
import com.example.sm.service.ServiceInterface;
import com.example.sm.service.coreservice.ProfessorCoreService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorCRUDService implements ServiceInterface<ProfessorDTO>, CRUDServiceInterface<Professor, ProfessorDTO> {

    private final ProfessorCoreService professorCoreService;
    private final PasswordEncoder passwordEncoder;

    public ProfessorCRUDService(ProfessorCoreService professorCoreService, PasswordEncoder passwordEncoder) {
        this.professorCoreService = professorCoreService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ProfessorDTO save(ProfessorDTO professorDTO) {
        return toDTO(professorCoreService.save(fromDTO(professorDTO)));
    }

    @Override
    public List<ProfessorDTO> getAll() {

        List<ProfessorDTO> professorDTOList = new ArrayList<>();
        List<Professor> professors = professorCoreService.getAll();
        for (Professor professor : professors) {
            professorDTOList.add(toDTO(professor));
        }
        return professorDTOList;
    }

    @Override
    public List<ProfessorDTO> getAllPaginated(int page, int size) {

        List<ProfessorDTO> professorDTOList = new ArrayList<>();
        List<Professor> professors = professorCoreService.getAllPaginated(page, size);
        for (Professor professor : professors) {
            professorDTOList.add(toDTO(professor));
        }
        return professorDTOList;
    }

    @Override
    public ProfessorDTO get(Integer id) {
        return toDTO(professorCoreService.get(id));
    }

    @Override
    public ProfessorDTO update(ProfessorDTO professorDTO, Integer id) {
        return toDTO(professorCoreService.update(fromDTO(professorDTO), id));
    }

    @Override
    public void delete(Integer id) {
        professorCoreService.delete(id);
    }

    @Override
    public ProfessorDTO toDTO(Professor professor) {

        String password = "*******";
        return new ProfessorDTO(
                professor.getId(), professor.getUsername(), password, professor.getFirstname(), professor.getLastname(),
                professor.getNationalId(), professor.getPersonnelId(), professor.getFaculty());
    }

    @Override
    public Professor fromDTO(ProfessorDTO professorDTO) {

        String encodedPass = passwordEncoder.encode(professorDTO.getPassword());
        return new Professor(
                professorDTO.getId(), professorDTO.getUsername(), encodedPass, professorDTO.getFirstName(), professorDTO.getLastName(),
                professorDTO.getNationalId(), professorDTO.getPersonnelId(), professorDTO.getFaculty());
    }
}
