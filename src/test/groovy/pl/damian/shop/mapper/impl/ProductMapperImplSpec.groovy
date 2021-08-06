package pl.damian.shop.mapper.impl

import pl.damian.shop.domain.dao.Product
import pl.damian.shop.domain.dto.ProductDto
import pl.damian.shop.mapper.ProductMapperImpl
import spock.lang.Specification

class ProductMapperImplSpec extends Specification {
    def productMapper = new ProductMapperImpl()

    def 'test daoToDto'() {
        given:
        def product = new Product(id: 1l,brand:'LG',type: 'TV',price: 2000l,quantity: 1)

        when:
        def result = productMapper.daoToDto(product)

        then:
        result.getId() == product.getId()//wszystkie pola do porównania, jeżeli null też sprawdzamy
        result.getName()==product.name
        result.brand==product.brand
        result.type==product.type
        result.price==product.price
        result.quantity==product.quantity
    }

    def 'test dtoToDao'() {
        given:
        def productDto = new ProductDto(id: 1l,brand:'LG',type: 'TV',price: 2000l,quantity: 1)

        when:
        def result = productMapper.dtoToDao(productDto)

        then:
        result.getId() == productDto.getId()//wszystkie pola do porównania, jeżeli null też sprawdzamy
        result.getName()==productDto.name
        result.brand==productDto.brand
        result.type==productDto.type
        result.price==productDto.price
        result.quantity==productDto.quantity

    }

}
