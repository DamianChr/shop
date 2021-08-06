package pl.damian.shop.service.impl

import pl.damian.shop.domain.dao.Category
import pl.damian.shop.repository.CategoryRepository
import pl.damian.shop.service.Impl.CategoryServiceImpl
import spock.lang.Specification

class CategoryServiceImplSpec extends Specification {

    def categoryService
    def categoryRepository = Mock(CategoryRepository)

    def setup(){
        categoryService=new CategoryServiceImpl(categoryRepository)
    }

    //ok
    def'test saveCategory'(){
        given:
        def category=new Category(id:2l,name:'category',category: null)

        when:
        categoryService.saveCategory(category)

        then:
        1*categoryRepository.save(category)

    }


    //ok
    def'test find category by id'(){
        when:
        categoryService.findCategoryById(2l)

        then:
        1*categoryRepository.getOne(2l)
    }

    //Category?
    def'test update category'(){
        given:
        def category=new Category(name:'category')
        categoryRepository.getOne(2l)>> new Category(id:2l,name:'category')

        when:
        def result= categoryService.updateCategory(category,2l)

        then:
        result.id==2l
        result.name==category.name

    }


    def'test delete category'(){
        when:
        categoryService.deleteCategoryById(2l)

        then:

        1*categoryRepository.deleteById(2l)

    }
//
//    def'test getPage'(){
//        given:
//
//        when:
//        categoryService.getPage()
//    }
}
