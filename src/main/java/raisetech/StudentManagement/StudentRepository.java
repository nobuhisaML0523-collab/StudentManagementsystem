package raisetech.StudentManagement;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentRepository {

  @Select("SELECT id, name, name_kana, nickname, email, city, age, gender FROM students")
  List<Student> getStudentsList();

  @Select("SELECT id, student_id, course_name, start_date, end_date FROM students_courses")
  List<Course> getStudentsCourses();
}
