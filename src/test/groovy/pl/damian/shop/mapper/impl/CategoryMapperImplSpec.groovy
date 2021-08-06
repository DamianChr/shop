package pl.damian.shop.mapper.impl


import pl.damian.shop.domain.dao.Category
import pl.damian.shop.domain.dto.CategoryDto
import pl.damian.shop.mapper.CategoryMapperImpl
import spock.lang.Specification

class CategoryMapperImplSpec extends Specification {

    def categoryMapper = new CategoryMapperImpl()

    def 'test daoToDto'() {
        given:
        def category = new Category(id: 1l, name: 'test')

        when:
        def result = categoryMapper.daoToDto(category)

        then:
        result.getId() == category.getId()

    }

    def 'test dtoToDao'() {
        given:
        def categoryDto = new CategoryDto(id: 1l, name: 'test')

        when:
        def result = categoryMapper.dtoToDao(categoryDto)

        then:
        result.getId() == categoryDto.getId()

    }
}
