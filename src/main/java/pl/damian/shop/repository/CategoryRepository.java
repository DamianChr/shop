package pl.damian.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.damian.shop.domain.dao.Category;

import java.util.Locale;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
