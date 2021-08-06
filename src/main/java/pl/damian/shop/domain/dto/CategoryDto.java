package pl.damian.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotBlank;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {

    private Long id;
    @NotBlank(message = "Name  can not be a whitespace")
    private String name;
}
