package raisetech.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
  private String id;
  private String name;
  private String nameKana;
  private String nickname;
  private String email;
  private String city;
  private int age;
  private String gender;
}
