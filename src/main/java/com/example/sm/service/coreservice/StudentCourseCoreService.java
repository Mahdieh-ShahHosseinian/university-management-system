package com.example.sm.service.coreservice;

import com.example.sm.dao.StudentCourseRepository;
import com.example.sm.model.Professor;
import com.example.sm.model.StudentCourse;
import com.example.sm.model.StudentCourseId;
import com.example.sm.service.ServiceInterface;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentCourseCoreService implements ServiceInterface<StudentCourse, StudentCourseId> {

    private final StudentCourseRepository repository;
    private final ProfessorCoreService professorCoreService;
    private final CourseCoreService courseCoreService;
    private final StudentCoreService studentCoreService;

    // added @Lazy to solve circular references with StudentCourseCoreService class
    public StudentCourseCoreService(StudentCourseRepository repository, ProfessorCoreService professorCoreService, CourseCoreService courseCoreService, @Lazy StudentCoreService studentCoreService) {
        this.repository = repository;
        this.professorCoreService = professorCoreService;
        this.courseCoreService = courseCoreService;
        this.studentCoreService = studentCoreService;
    }

    @Override
    public StudentCourse save(StudentCourse studentCourse) {
        return repository.save(studentCourse);
    }

    public StudentCourse save(Integer studentId, Integer professorId, Integer courseId) {
        StudentCourse sc = new StudentCourse(
                studentCoreService.get(studentId), professorCoreService.get(professorId), courseCoreService.get(courseId));
        return repository.save(sc);
    }

    @Override
    public List<StudentCourse> getAll() {
        return repository.findAll();
    }

    @Override
    public List<StudentCourse> getAllPaginated(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).toList();
    }

    @Override
    public StudentCourse get(StudentCourseId id) {
        return repository.findByStudentCourseId(id);
    }

    @Override
    public StudentCourse update(StudentCourse studentCourse, StudentCourseId id) {

        StudentCourse sc = get(id);
        sc.setGrade(studentCourse.getGrade());
        return save(sc);
    }

    public StudentCourse update(Integer studentId, Integer professorId, Integer courseId, Double grade) {

        StudentCourse sc = repository.findByStudentCourseIdStudentAndStudentCourseIdProfessorAndStudentCourseIdCourse(
                studentCoreService.get(studentId), professorCoreService.get(professorId), courseCoreService.get(courseId));
        sc.setGrade(grade);
        return repository.save(sc);
    }

    @Override
    public void delete(StudentCourseId id) {
        repository.deleteByStudentCourseId(id);
    }

    public Set<StudentCourse> findByProfessor(Professor professor) {
        return repository.findByStudentCourseIdProfessor(professor);
    }
}