package pl.damian.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.damian.shop.domain.dto.ProductDto;
import pl.damian.shop.mapper.ProductMapper;
import pl.damian.shop.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;


    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productMapper.daoToDto(productService.findProductById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ProductDto saveProduct(@RequestBody @Valid ProductDto productDto) {
        return productMapper.daoToDto(productService.saveProduct(productMapper.dtoToDao(productDto), productDto.getCategoryId()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ProductDto updateProduct(@RequestBody ProductDto product, @PathVariable Long id) {
        return productMapper.daoToDto(productService.updateProduct(productMapper.dtoToDao(product), id, product.getCategoryId()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @GetMapping
    public Page<ProductDto> productPage(@RequestParam int page, @RequestParam int size) {
        return productService.getPage(PageRequest.of(page, size)).map(productMapper::daoToDto);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDto> getProductByCategory(@PathVariable Long categoryId) {
        return productMapper.listDaoToListDto(productService.getProductByCategory(categoryId));
    }


}
