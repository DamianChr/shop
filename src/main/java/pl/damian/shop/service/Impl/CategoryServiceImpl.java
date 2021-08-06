package pl.damian.shop.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.damian.shop.domain.dao.Category;
import pl.damian.shop.repository.CategoryRepository;
import pl.damian.shop.service.CategoryService;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.getOne(id);
    }

    @Transactional
    @Override
    public Category updateCategory(Category category, Long id) {
        Category categoryDb = findCategoryById(id);
        categoryDb.setName(category.getName());

        return categoryDb;
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);

    }

    @Override
    public Page<Category> getPage(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
}
