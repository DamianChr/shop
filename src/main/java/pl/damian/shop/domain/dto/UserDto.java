package pl.damian.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.damian.shop.validator.PasswordValid;
import pl.damian.shop.validator.group.UserCreate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ApiModel
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@PasswordValid(groups = UserCreate.class)
public class UserDto {


    private Long id;
    @NotBlank(message = "First name can not be a whitespace")
    private String firstName;
    @NotBlank(message = "Last name can not be a whitespace")
    private String lastName;
    @NotBlank(message = "Password  can not be a whitespace")
    private String password;
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Mobile  can not be a whitespace")
    private String mobile;
    private String confirmPassword;

}
