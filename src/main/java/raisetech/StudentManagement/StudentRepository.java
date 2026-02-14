package raisetech.StudentManagement;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StudentRepository {

  @Select("SELECT id, name, age FROM student")
  List<Student> allStudents();

  @Select("SELECT * FROM student WHERE id = #{id}")
  Student searchById(int id);

  @Insert("INSERT student (id, name, age) values(#{id}, #{name}, #{age})")
  void registerStudent(int id, String name, int age);

  @Update("UPDATE student SET name = #{name} WHERE id = #{id}")
  void updateName(int id,String name);

  @Update("UPDATE student SET age = #{age} WHERE id = #{id}")
  void updateAge(int id, int age);

  @Delete("DELETE FROM student WHERE id = #{id}")
  void deleteStudent(int id);
}
