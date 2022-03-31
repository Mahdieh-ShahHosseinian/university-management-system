package com.example.sm.service.crudservice;

import com.example.sm.dto.FacultyDTO;
import com.example.sm.model.Faculty;
import com.example.sm.service.ServiceInterface;
import com.example.sm.service.coreservice.FacultyCoreService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyCRUDService implements ServiceInterface<FacultyDTO>, CRUDServiceInterface<Faculty, FacultyDTO> {

    private final FacultyCoreService facultyCoreService;

    public FacultyCRUDService(FacultyCoreService facultyCoreService) {
        this.facultyCoreService = facultyCoreService;
    }

    @Override
    public FacultyDTO save(FacultyDTO facultyDTO) {
        return toDTO(facultyCoreService.save(fromDTO(facultyDTO)));
    }

    @Override
    public List<FacultyDTO> getAll() {

        List<FacultyDTO> facultyDTOList = new ArrayList<>();
        List<Faculty> faculties = facultyCoreService.getAll();
        for (Faculty faculty : faculties) {
            facultyDTOList.add(toDTO(faculty));
        }
        return facultyDTOList;
    }

    @Override
    public List<FacultyDTO> getAllPaginated(int page, int size) {

        List<FacultyDTO> facultyDTOList = new ArrayList<>();
        List<Faculty> faculties = facultyCoreService.getAllPaginated(page, size);
        for (Faculty faculty : faculties) {
            facultyDTOList.add(toDTO(faculty));
        }
        return facultyDTOList;
    }

    @Override
    public FacultyDTO get(Integer id) {
        return toDTO(facultyCoreService.get(id));
    }

    @Override
    public FacultyDTO update(FacultyDTO facultyDTO, Integer id) {
        return toDTO(facultyCoreService.update(fromDTO(facultyDTO), id));
    }

    @Override
    public void delete(Integer id) {
        facultyCoreService.delete(id);
    }

    @Override
    public FacultyDTO toDTO(Faculty faculty) {
        return new FacultyDTO(faculty.getId(), faculty.getName());
    }

    @Override
    public Faculty fromDTO(FacultyDTO facultyDTO) {
        return new Faculty(facultyDTO.getId(), facultyDTO.getName());
    }
}
