package pl.damian.shop.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.damian.shop.domain.dao.Product;

import java.util.List;

public interface ProductService {

    @CachePut(cacheNames = "product", key = "#result.id")
    Product saveProduct(Product product, Long categoryId);

    @Cacheable(cacheNames = "product",key = "#id")
    Product findProductById(Long id);

    @CachePut(cacheNames = "product", key = "#result.id")
    Product updateProduct(Product product, Long id, Long categoryId);

    @CacheEvict(cacheNames = "product", key = "#id")
    void deleteProductById(Long id);

    Page<Product> getPage(Pageable pageable);

    List<Product> getProductByCategory(Long categoryId);


}
