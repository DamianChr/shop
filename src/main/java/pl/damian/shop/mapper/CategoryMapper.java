package pl.damian.shop.mapper;

import org.mapstruct.Mapper;
import pl.damian.shop.domain.dao.Category;
import pl.damian.shop.domain.dto.CategoryDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category dtoToDao(CategoryDto categoryDto);

    CategoryDto daoToDto (Category category);
}
