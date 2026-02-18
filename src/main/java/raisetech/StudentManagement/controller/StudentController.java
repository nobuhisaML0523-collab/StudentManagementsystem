package raisetech.StudentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.service.StudentService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/student")
public class StudentController {
  private final StudentService service;
  private StudentConverter converter;

  // ★ コンストラクタ注入
  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  /**
   * HTTPでの表示
   */
  @GetMapping("/list")
  public String getStudentsListJson(Model model) {
    List<Student> students = service.searchStudentsList();
    List<StudentsCourses> studentsCourses = service.searchStudentsCourses();

    model.addAttribute("studentList", converter.convertStudentDetails(students, studentsCourses));

    return "studentList";
  }

}
