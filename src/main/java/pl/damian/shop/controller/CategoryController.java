package pl.damian.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import pl.damian.shop.domain.dto.CategoryDto;
import pl.damian.shop.mapper.CategoryMapper;
import pl.damian.shop.service.CategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryMapper.daoToDto(categoryService.findCategoryById(id));
    }

    @PostMapping
    public CategoryDto saveCategory(@RequestBody CategoryDto categoryDto) {
        return categoryMapper.daoToDto(categoryService.saveCategory(categoryMapper.dtoToDao(categoryDto)));
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@RequestBody CategoryDto category, @PathVariable Long id) {
        return categoryMapper.daoToDto(categoryService.updateCategory(categoryMapper.dtoToDao(category), id));
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }

    @GetMapping
    public Page<CategoryDto> categoryPage(@RequestParam int page, @RequestParam int size) {
        return categoryService.getPage(PageRequest.of(page, size)).map(categoryMapper::daoToDto);
    }

}
