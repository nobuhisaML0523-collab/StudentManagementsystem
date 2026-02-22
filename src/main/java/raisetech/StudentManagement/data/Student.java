package raisetech.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class Student {
  private String id;

  @NotBlank(message = "名前は必須です")
  private String name;

  private String nameKana;
  private String nickname;

  @NotBlank(message = "メールアドレスは必須です")
  @Email(message = "メールアドレスの形式が正しくありません")
  private String email;

  @NotBlank(message = "地域は必須です")
  private String city;

  @NotNull(message = "年齢は必須です")
  private int age;
  private String gender;
  private String remark;
  private boolean isDeleted;
}
