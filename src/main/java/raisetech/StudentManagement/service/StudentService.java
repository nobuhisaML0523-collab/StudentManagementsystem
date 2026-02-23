package raisetech.StudentManagement.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
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
    return repository.getALLStudents();
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

  public StudentDetail getStudentDetailForEdit(String studentId) {
    Student student = repository.findStudentById(studentId);
    List<StudentsCourses> courses = repository.findCoursesByStudentId(studentId);

    // ★ null を絶対に残さない
    if (student.getDeleted() == null) {
      student.setDeleted(false);
    }

    StudentDetail detail = new StudentDetail();
    detail.setStudent(student);
    detail.setCourses(courses);

    return detail;
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

  public void updateStudent(StudentDetail studentDetail) {

    Student student = studentDetail.getStudent();
    //StudentsCourses course = studentDetail.getCourses().get(0);

    // null 対策（重要）
    if (student.getDeleted() == null) {
      student.setDeleted(false);
    }

    repository.updateStudent(student);

  }

  public List<Student> searchStudentsList(boolean includeDeleted) {
    if (includeDeleted) {
      return repository.getALLStudents();
    } else {
      return repository.getActiveStudents();
    }
  }

}
