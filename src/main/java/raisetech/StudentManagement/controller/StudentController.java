package raisetech.StudentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    model.addAttribute("studentList", converter.convertStudentDetails(students, studentsCourses));

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

  @GetMapping("/editStudent/{id}")
  public String editStudent(@PathVariable String id, Model model) {
    model.addAttribute(
        "studentDetail",
        service.getStudentDetailForEdit(id)
    );
    return "editStudent";
  }

  @PostMapping("/updateStudent")
  public String updateStudent(@ModelAttribute StudentDetail studentDetail, @RequestParam(name = "deleteFlag", defaultValue = "false") boolean deleteFlag) {

    service.updateStudent(studentDetail, deleteFlag);

    return "redirect:/studentList";
  }

  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail) {

    Student student = studentDetail.getStudent();

    // ★ null / 空チェック
    if (studentDetail.getCourses() == null || studentDetail.getCourses().isEmpty()) {
      throw new IllegalStateException("コース情報が送信されていません");
    }


    StudentsCourses course = studentDetail.getCourses().get(0);

    service.registerStudent(student, course);

    return "redirect:/studentList";
  }
}
