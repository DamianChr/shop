package pl.damian.shop.mapper;

import org.mapstruct.Mapper;
import pl.damian.shop.domain.dao.Product;
import pl.damian.shop.domain.dto.ProductDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product dtoToDao(ProductDto productDto);

    ProductDto daoToDto(Product product);

    List<ProductDto> listDaoToListDto(List<Product> productList);
}
