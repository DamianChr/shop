package pl.damian.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    private Long id;
    @NotBlank(message = "Name  can not be a whitespace")
    private String name;
    @NotBlank(message = "Brand name can not be a whitespace")
    private String brand;
    @NotBlank(message = "Type name can not be a whitespace")
    private String type;
    @Min(value = 1,message = "Price can not be below 1")
    private Double price;
    @Min(value =0,message = "Value can not be below 0")
    private Integer quantity;
    @NotNull
    private Long categoryId;
}
