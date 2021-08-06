package pl.damian.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.damian.shop.domain.dao.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List <Product> findByCategoryId(Long categoryId);



}
