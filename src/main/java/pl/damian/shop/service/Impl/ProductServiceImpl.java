package pl.damian.shop.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.damian.shop.domain.dao.Product;
import pl.damian.shop.repository.ProductRepository;
import pl.damian.shop.service.CategoryService;
import pl.damian.shop.service.ProductService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public Product saveProduct(Product product, Long categoryId) {
        product.setCategory(categoryService.findCategoryById(categoryId));
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) {
        log.info("Product with id {} not in cache",id);
        return productRepository.getOne(id);
    }


    @Transactional
    @Override
    public Product updateProduct(Product product, Long id,Long categoryId) {
        Product productDB = findProductById(id);
        productDB.setBrand(product.getBrand());
        productDB.setPrice(product.getPrice());
        productDB.setName(product.getName());
        productDB.setQuantity(product.getQuantity());
        productDB.setType(product.getType());
        productDB.setCategory(categoryService.findCategoryById(categoryId));

        return productDB;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> getProductByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }


}
