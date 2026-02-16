package raisetech.StudentManagement;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
  private final StudentRepository repository;

  // ★ コンストラクタ注入（推奨）
  public StudentController(StudentRepository repository) {
    this.repository = repository;
  }

  /**
   * JSON版：全件取得（REST向け）
   */
  @GetMapping("/list")
  public List<Student> getStudentsListJson() {
    return repository.getStudentsList();
  }

  @GetMapping("/courses")
  public List<Course> getStudentsCoursesJson() {
    return repository.getStudentsCourses();
  }

}
