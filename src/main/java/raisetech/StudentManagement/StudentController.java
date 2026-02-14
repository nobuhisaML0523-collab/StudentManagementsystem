package raisetech.StudentManagement;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
  private final StudentRepository repository;

  // ★ コンストラクタ注入（推奨）
  public StudentController(StudentRepository repository) {
    this.repository = repository;
  }

  // 全件取得
  @GetMapping
  public String getAllStudents() {
    List<Student> studentList = repository.allStudents();
    StringBuilder result = new StringBuilder();

    for (Student student : studentList) {
      result.append(student.getName())
          .append(" ")
          .append(student.getAge())
          .append("歳")
          .append("\n");
    }

    return result.toString();
  }

  // ID検索
  @GetMapping("/search")
  public ResponseEntity<String> getStudents(@RequestParam int id) {
    Student student = repository.searchById(id);

    if (student == null) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body("指定されたIDの学生は存在しません");
    }

    return ResponseEntity.ok(
        student.getName() + " " + student.getAge() + "歳"
    );
  }

  // 登録
  @PostMapping
  public ResponseEntity<String> registerStudent(
      @RequestParam int id,
      @RequestParam String name,
      @RequestParam int age) {

    repository.registerStudent(id, name, age);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body("登録しました");
  }

  // 名前更新
  @PatchMapping("/name")
  public ResponseEntity<String> setStudentName(
      @RequestParam int id,
      @RequestParam String name) {

    Student student = repository.searchById(id);
    if (student == null) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body("指定されたIDの学生は存在しません");
    }

    repository.updateName(id, name);
    return ResponseEntity.ok("名前を更新しました");
  }

  // 年齢更新
  @PatchMapping("/age")
  public ResponseEntity<String> setStudentAge(
      @RequestParam int id,
      @RequestParam int age) {

    Student student = repository.searchById(id);
    if (student == null) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body("指定されたIDの学生は存在しません");
    }

    repository.updateAge(id, age);
    return ResponseEntity.ok("年齢を更新しました");
  }

  // 削除
  @DeleteMapping
  public ResponseEntity<Void> deleteStudent(@RequestParam int id) {

    Student student = repository.searchById(id);
    if (student == null) {
      return ResponseEntity.notFound().build();
    }

    repository.deleteStudent(id);
    return ResponseEntity.noContent().build();
  }
}
