package raisetech.StudentManagement;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StudentRepository {

  @Select("SELECT id, name, age FROM student")
  List<Student> allStudents();

  @Select("SELECT * FROM student WHERE id = #{id}")
  Student searchById(@Param("id") int id);

  @Insert("INSERT INTO student (id, name, age) values(#{id}, #{name}, #{age})")
  void registerStudent(@Param("id") int id, @Param("name") String name, @Param("age") int age);

  @Update("UPDATE student SET name = #{name} WHERE id = #{id}")
  void updateName(@Param("id") int id,@Param("name") String name);

  @Update("UPDATE student SET age = #{age} WHERE id = #{id}")
  void updateAge(@Param("id") int id, @Param("age") int age);

  @Delete("DELETE FROM student WHERE id = #{id}")
  void deleteStudent(@Param("id") int id);
}
