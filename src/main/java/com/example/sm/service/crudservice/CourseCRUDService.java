package com.example.sm.service.crudservice;

import com.example.sm.dto.CourseDTO;
import com.example.sm.model.Course;
import com.example.sm.service.ServiceInterface;
import com.example.sm.service.coreservice.CourseCoreService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseCRUDService implements ServiceInterface<CourseDTO, Integer>, CRUDServiceInterface<Course, CourseDTO> {

    private final CourseCoreService courseCoreService;

    public CourseCRUDService(CourseCoreService courseCoreService) {
        this.courseCoreService = courseCoreService;
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
        return new CourseDTO(course.getId(), course.getName(), course.getUnit(), course.getFaculty());
    }

    @Override
    public Course fromDTO(CourseDTO courseDTO) {
        return new Course(courseDTO.getId(), courseDTO.getName(), courseDTO.getUnit(), courseDTO.getFaculty());
    }
}
