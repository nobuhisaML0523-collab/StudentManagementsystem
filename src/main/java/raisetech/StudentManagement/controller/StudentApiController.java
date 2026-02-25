package raisetech.StudentManagement.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentUpdateRequest;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

@RestController
public class StudentApiController {
  private final StudentService service;
  private final StudentConverter converter;

  @Autowired
  public StudentApiController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentApiList")
  public List<StudentDetail> getStudentsList() {
    List<Student> students = service.searchStudentsList();
    List<StudentsCourses> studentsCourses = service.searchStudentsCourses();

    //model.addAttribute("studentList", converter.convertStudentDetails(students, studentsCourses));

    return converter.convertStudentDetails(students, studentsCourses);
  }

  @PostMapping("/updateStudent")
  public ResponseEntity<?> updateStudent(
      @RequestBody @Valid StudentUpdateRequest req
  ) {
    service.updateStudent(req);
    return ResponseEntity.ok(Map.of("message", "更新処理が成功しました"));
  }

}
