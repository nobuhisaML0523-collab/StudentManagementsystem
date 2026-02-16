package raisetech.StudentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.data.Course;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
  private final StudentService service;

  // ★ コンストラクタ注入（推奨）
  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * JSON版：全件取得（REST向け）
   */
  @GetMapping("/list")
  public List<Student> getStudentsListJson() {
    return service.searchStudentsList();
  }

  @GetMapping("/courses")
  public List<Course> getStudentsCoursesJson() {
    return service.searchStudentsCourses();
  }

}
