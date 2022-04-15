package com.example.sm.service.crudservice;

import com.example.sm.dto.StudentDTO;
import com.example.sm.model.Student;
import com.example.sm.service.ServiceInterface;
import com.example.sm.service.mapper.ModelMapper;
import com.example.sm.service.coreservice.StudentCoreService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCRUDService implements ServiceInterface<StudentDTO, Integer>, CRUDServiceInterface<Student, StudentDTO> {

    private final StudentCoreService studentCoreService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public StudentCRUDService(StudentCoreService studentCoreService, PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.studentCoreService = studentCoreService;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        return toDTO(studentCoreService.save(fromDTO(studentDTO)));
    }

    @Override
    public List<StudentDTO> getAll() {

        List<StudentDTO> studentDTOList = new ArrayList<>();
        List<Student> students = studentCoreService.getAll();
        for (Student student : students) {
            studentDTOList.add(toDTO(student));
        }
        return studentDTOList;
    }

    @Override
    public List<StudentDTO> getAllPaginated(int page, int size) {

        List<StudentDTO> studentDTOList = new ArrayList<>();
        List<Student> students = studentCoreService.getAllPaginated(page, size);
        for (Student student : students) {
            studentDTOList.add(toDTO(student));
        }
        return studentDTOList;
    }

    @Override
    public StudentDTO get(Integer id) {
        return toDTO(studentCoreService.get(id));
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO, Integer id) {
        return toDTO(studentCoreService.update(fromDTO(studentDTO), id));
    }

    @Override
    public void delete(Integer id) {
        studentCoreService.delete(id);
    }

    public void addCourse(Integer id, Integer professorId, Integer courseId) {
        studentCoreService.addCourse(id, professorId, courseId);
    }

    public Double getAverage(Integer id) {
        return studentCoreService.getAverage(id);
    }

    @Override
    public StudentDTO toDTO(Student student) {
        String password = "*******";
        student.setPassword(password);
        return (StudentDTO) mapper.map(student, StudentDTO.class);
    }

    @Override
    public Student fromDTO(StudentDTO studentDTO) {
        String encodedPass = passwordEncoder.encode(studentDTO.getPassword());
        studentDTO.setPassword(encodedPass);
        return (Student) mapper.map(studentDTO, Student.class);
    }
}
