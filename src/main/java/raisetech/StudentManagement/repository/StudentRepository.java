package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentManagement.data.Course;
import raisetech.StudentManagement.data.Student;

/**
 * 受講生情報を扱うリポジトリ*
 * 全件検索や単一条件での検索、コース情報の検索が行えるクラスです
 */


@Mapper
public interface StudentRepository {

  @Select("SELECT id, name, name_kana, nickname, email, city, age, gender FROM students")
  List<Student> getStudentsList();

  @Select("SELECT id, student_id, course_name, start_date, end_date FROM students_courses")
  List<Course> getStudentsCourses();
}
