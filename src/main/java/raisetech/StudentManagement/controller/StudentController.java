package raisetech.StudentManagement.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;
import org.springframework.ui.Model;

@Controller
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
  @GetMapping("/studentList")
  public String getStudentsList(Model model) {
    List<Student> students = service.searchStudentsList();
    List<StudentsCourses> studentsCourses = service.searchStudentsCourses();

    model.addAttribute(
        "studentList",
        converter.convertStudentDetails(students, studentsCourses)
    );

    return "studentList";
  }

  @GetMapping("/newstudent")
  public String newStudent(Model model){
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(new Student());

    studentDetail.setCourses(List.of(new StudentsCourses()));

    model.addAttribute("studentDetail", studentDetail);
    return "registerStudent";
  }

  @PostMapping("/registerStudent")
  public String registerStudent(
      @Valid @ModelAttribute StudentDetail studentDetail,
      BindingResult result) {

    // ★ Student の入力エラー
    if (result.hasErrors()) {
      return "registerStudent";
    }

    // ★ コース未選択チェック（既存ロジック）
    if (studentDetail.getCourses() == null
        || studentDetail.getCourses().isEmpty()
        || studentDetail.getCourses().get(0).getCourseName() == null
        || studentDetail.getCourses().get(0).getCourseName().isBlank()) {

      result.reject("course.empty", "コースを選択してください");
      return "registerStudent";
    }

    service.registerStudent(
        studentDetail.getStudent(),
        studentDetail.getCourses().get(0)
    );

    return "redirect:/studentList";
  }
}
