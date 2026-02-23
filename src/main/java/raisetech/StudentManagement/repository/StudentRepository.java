package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;

/**
 * 受講生情報を扱うリポジトリ*
 * 全件検索や単一条件での検索、コース情報の検索が行えるクラスです
 */


@Mapper
public interface StudentRepository {

  @Select("SELECT id, name, name_kana, nickname, email, city, age, gender, remark, is_deleted AS deleted FROM students")
  List<Student> getALLStudents();

  @Select(" SELECT id, name, name_kana, nickname, email, city, age, gender, remark, is_deleted FROM students WHERE is_deleted = false")
  List<Student> getActiveStudents();

  @Select("SELECT id, student_id, course_name, start_date, end_date FROM students_courses")
  List<StudentsCourses> getStudentsCourses();

  @Select("SELECT MAX(id) FROM students")
  String findMaxStudentId();

  @Select("SELECT MAX(id) FROM students_courses")
  String findMaxStudentCourseId();

  @Select("SELECT id, name, name_kana, nickname, email, city, age, gender, remark, is_deleted AS deleted FROM students WHERE id = #{id} ")
  Student findStudentById(String id);

  @Select(" SELECT id, student_id, course_name, start_date, end_date FROM students_courses WHERE student_id = #{studentId} ")
  List<StudentsCourses> findCoursesByStudentId(String studentId);

  @Insert("INSERT INTO students(id, name, name_kana, nickname, email, city, age, gender, remark, is_deleted) VALUES(#{id}, #{name}, #{nameKana}, #{nickname}, #{email}, #{city}, #{age}, #{gender}, #{remark}, false)")
  void insertStudent(Student student);

  @Insert("INSERT INTO students_courses(id, student_id, course_name, start_date, end_date) VALUES(#{id}, #{studentId}, #{courseName}, #{startDate}, #{endDate})")
  void insertStudentCourse(StudentsCourses course);

  @Update("UPDATE students SET email = #{email}, city = #{city}, remark = #{remark}, is_deleted = #{deleted} WHERE id = #{id}")
  void updateStudent(Student student);

}
