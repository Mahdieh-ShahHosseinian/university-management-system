package com.example.sm.service.crudservice;

import com.example.sm.dto.CourseDTO;
import com.example.sm.model.Course;
import com.example.sm.service.ServiceInterface;
import com.example.sm.service.mapper.ModelMapper;
import com.example.sm.service.coreservice.CourseCoreService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseCRUDService implements ServiceInterface<CourseDTO, Integer>, CRUDServiceInterface<Course, CourseDTO> {

    private final CourseCoreService courseCoreService;
    private final ModelMapper mapper;

    public CourseCRUDService(CourseCoreService courseCoreService, ModelMapper mapper) {
        this.courseCoreService = courseCoreService;
        this.mapper = mapper;
    }

    @Override
    public CourseDTO save(CourseDTO courseDTO) {
        return toDTO(courseCoreService.save(fromDTO(courseDTO)));
    }

    @Override
    public List<CourseDTO> getAll() {

        List<CourseDTO> courseDTOList = new ArrayList<>();
        List<Course> courses = courseCoreService.getAll();
        for (Course course : courses) {
            courseDTOList.add(toDTO(course));
        }
        return courseDTOList;
    }

    @Override
    public List<CourseDTO> getAllPaginated(int page, int size) {

        List<CourseDTO> courseDTOList = new ArrayList<>();
        List<Course> courses = courseCoreService.getAllPaginated(page, size);
        for (Course course : courses) {
            courseDTOList.add(toDTO(course));
        }
        return courseDTOList;
    }

    @Override
    public CourseDTO get(Integer id) {
        return toDTO(courseCoreService.get(id));
    }

    @Override
    public CourseDTO update(CourseDTO courseDTO, Integer id) {
        return toDTO(courseCoreService.update(fromDTO(courseDTO), id));
    }

    @Override
    public void delete(Integer id) {
        courseCoreService.delete(id);
    }

    @Override
    public CourseDTO toDTO(Course course) {
        return (CourseDTO) mapper.map(course, CourseDTO.class);
    }

    @Override
    public Course fromDTO(CourseDTO courseDTO) {
        return (Course) mapper.map(courseDTO, Course.class);
    }
}
