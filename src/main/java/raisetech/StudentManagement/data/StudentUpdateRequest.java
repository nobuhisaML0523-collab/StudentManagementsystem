package raisetech.StudentManagement.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentUpdateRequest {

  @NotBlank
  private String id;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String city;

  private String remark;

}
