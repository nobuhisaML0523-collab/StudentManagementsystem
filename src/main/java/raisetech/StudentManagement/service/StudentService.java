package raisetech.StudentManagement.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentUpdateRequest;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.repository.StudentRepository;

@Service
@Transactional
public class StudentService {
  private final StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository){
    this.repository = repository;
  }

  public List<Student> searchStudentsList() {
    return repository.getStudentsList();
  }

  public List<StudentsCourses> searchStudentsCourses() {
    return repository.getStudentsCourses();
  }

  public void registerStudent(Student student,StudentsCourses course) {

    // =====================
    // remark 未入力対策
    // =====================
    if (student.getRemark() == null || student.getRemark().isBlank()) {
      student.setRemark("特になし");
    }

    // =====================
    // student ID 採番
    // =====================
    String maxStudentId = repository.findMaxStudentId();
    String nextStudentId = generateNextStudentId(maxStudentId);
    student.setId(nextStudentId);

    repository.insertStudent(student);

    // =====================
    // course ID 採番
    // =====================
    String maxCourseId = repository.findMaxStudentCourseId();
    String nextCourseId = generateNextStudentCourseId(maxCourseId);

    course.setId(nextCourseId);
    course.setStudentId(nextStudentId);

    course.setStartDate(LocalDate.now()); // 登録日
    course.setEndDate(null);              // 終了日なし

    repository.insertStudentCourse(course);
  }

  private String generateNextStudentId(String maxId) {
    if (maxId == null) return "stu-001";
    int num = Integer.parseInt(maxId.substring(4));
    return String.format("stu-%03d", num + 1);
  }

  private String generateNextStudentCourseId(String maxId) {
    if (maxId == null) return "sc-001";
    int num = Integer.parseInt(maxId.substring(3));
    return String.format("sc-%03d", num + 1);
  }

  public void updateStudent(StudentUpdateRequest req) {

      repository.updateStudent(req);
  }

}
