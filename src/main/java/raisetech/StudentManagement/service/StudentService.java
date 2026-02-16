package raisetech.StudentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Course;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.repository.StudentRepository;

@Service
public class StudentService {
  private final StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository){
    this.repository = repository;
  }

  public List<Student> searchStudentsList() {
    return repository.getStudentsList().stream()
        .filter(student -> student.getAge() >= 30)
        .toList();
  }

  public List<Course> searchStudentsCourses() {
    return repository.getStudentsCourses().stream()
        .filter(course -> "JAVAフルコース".equals(course.getCourseName()))
        .toList();
  }
}
