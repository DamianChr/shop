package pl.damian.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.damian.shop.domain.dao.Category;

public interface CategoryService {

    Category saveCategory(Category category);

    Category findCategoryById(Long id);

    Category updateCategory(Category category,Long id);

    void deleteCategoryById(Long id);

    Page<Category> getPage(Pageable pageable);

}
