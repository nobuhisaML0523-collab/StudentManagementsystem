package raisetech.StudentManagement;

public class Student {
  private String name;
  private int age;
  private int id;

  // MyBatis 無引数コンストラクタ
  public Student() {
  }

  public Student(int id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
