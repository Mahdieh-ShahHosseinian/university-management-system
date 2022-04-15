package com.example.sm.service.coreservice;

import com.example.sm.dao.ProfessorRepository;
import com.example.sm.model.Course;
import com.example.sm.model.Professor;
import com.example.sm.model.Student;
import com.example.sm.model.StudentCourse;
import com.example.sm.service.ServiceInterface;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProfessorCoreService implements ServiceInterface<Professor, Integer> {

    private final ProfessorRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final CourseCoreService courseCoreService;
    private final StudentCourseCoreService studentCourseCoreService;

    public ProfessorCoreService(ProfessorRepository repository, PasswordEncoder passwordEncoder, CourseCoreService courseCoreService, @Lazy StudentCourseCoreService studentCourseCoreService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.courseCoreService = courseCoreService;
        this.studentCourseCoreService = studentCourseCoreService;
    }

    @Override
    public Professor save(Professor professor) {

        String encodedPass = passwordEncoder.encode(professor.getPassword());
        professor.setPassword(encodedPass);
        return repository.save(professor);
    }

    @Override
    public List<Professor> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Professor> getAllPaginated(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).toList();
    }

    @Override
    public Professor get(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Professor update(Professor professor, Integer id) {

        Professor p = get(id);
        p.setUsername(professor.getUsername());
        p.setPassword(professor.getPassword());
        p.setNationalId(professor.getNationalId());
        p.setFirstname(professor.getFirstname());
        p.setLastname(professor.getLastname());
        p.setFaculty(professor.getFaculty());
        return save(p);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public void addCourse(Integer id, Integer courseId) {

        Professor professor = get(id);
        Course course = courseCoreService.get(courseId);
        Set<Course> professorCourses = professor.getCourses();
        professorCourses.add(course);
        save(professor);
    }

    public Set<Course> getCourses(int id) {
        return get(id).getCourses();
    }

    public Set<Student> getStudents(int id) {

        Set<Student> students = new HashSet<>();
        Set<StudentCourse> studentCourses = studentCourseCoreService.findByProfessor(get(id));
        for (StudentCourse studentCourse : studentCourses) {
            students.add(studentCourse.getStudent());
        }
        return students;
    }

    public void setGrade(int id, int studentId, int courseId, double grade) {
        studentCourseCoreService.update(studentId, id, courseId, grade);
    }

    public Double getAverage(int id) {

        Set<Student> students = getStudents(id);
        double totalAverage = 0;
        int totalUnits = 0;
        for (Student student : students) {
            for (StudentCourse sc : student.getStudentCourses()) {

                totalAverage += sc.getGrade() * sc.getStudentCourseId().getCourse().getUnit();
                totalUnits += sc.getStudentCourseId().getCourse().getUnit();
            }
        }
        return totalAverage / totalUnits;
    }
}