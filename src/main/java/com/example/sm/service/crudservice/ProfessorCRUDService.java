package com.example.sm.service.crudservice;

import com.example.sm.dto.CourseDTO;
import com.example.sm.dto.ProfessorDTO;
import com.example.sm.dto.StudentDTO;
import com.example.sm.model.Course;
import com.example.sm.model.Professor;
import com.example.sm.model.Student;
import com.example.sm.service.ServiceInterface;
import com.example.sm.service.coreservice.ProfessorCoreService;
import com.example.sm.service.mapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProfessorCRUDService implements ServiceInterface<ProfessorDTO, Integer>, CRUDServiceInterface<Professor, ProfessorDTO> {

    private final ProfessorCoreService professorCoreService;
    private final PasswordEncoder passwordEncoder;
    private final CourseCRUDService courseCRUDService;
    private final StudentCRUDService studentCRUDService;
    private final ModelMapper mapper;

    public ProfessorCRUDService(ProfessorCoreService professorCoreService, PasswordEncoder passwordEncoder, CourseCRUDService courseCRUDService, StudentCRUDService studentCRUDService, ModelMapper mapper) {
        this.professorCoreService = professorCoreService;
        this.passwordEncoder = passwordEncoder;
        this.courseCRUDService = courseCRUDService;
        this.studentCRUDService = studentCRUDService;
        this.mapper = mapper;
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

    public void addCourse(Integer id, Integer courseId) {
        professorCoreService.addCourse(id, courseId);
    }

    public Set<CourseDTO> getCourses(int id) {

        Set<CourseDTO> courseDTOSet = new HashSet<>();
        Set<Course> courses = professorCoreService.getCourses(id);
        for (Course course : courses) {
            courseDTOSet.add(courseCRUDService.toDTO(course));
        }
        return courseDTOSet;
    }

    public Set<StudentDTO> getStudents(int id) {

        Set<StudentDTO> studentDTOSet = new HashSet<>();
        Set<Student> students = professorCoreService.getStudents(id);
        for (Student student : students) {
            studentDTOSet.add(studentCRUDService.toDTO(student));
        }
        return studentDTOSet;
    }

    public void setGrade(int id, int studentId, int courseId, double grade) {
        professorCoreService.setGrade(id, studentId, courseId, grade);
    }

    public Double getAverage(int id) {
        return professorCoreService.getAverage(id);
    }

    @Override
    public ProfessorDTO toDTO(Professor professor) {
        String password = "*******";
        professor.setPassword(password);
        return mapper.map(professor, ProfessorDTO.class);
    }

    @Override
    public Professor fromDTO(ProfessorDTO professorDTO) {
        String encodedPass = passwordEncoder.encode(professorDTO.getPassword());
        professorDTO.setPassword(encodedPass);
        return mapper.map(professorDTO, Professor.class);
    }
}
