package pl.damian.shop.service.impl

import pl.damian.shop.domain.dao.Category
import pl.damian.shop.domain.dao.Product
import pl.damian.shop.repository.ProductRepository
import pl.damian.shop.service.CategoryService
import pl.damian.shop.service.Impl.ProductServiceImpl
import spock.lang.Specification

class ProductServiceImplSpec extends Specification {


    def productService
    def productRepository = Mock(ProductRepository)
    def categoryService = Mock(CategoryService)


    def setup() {
        productService = Spy(ProductServiceImpl, constructorArgs: [productRepository, categoryService])
    }

    //category?
    def 'test save product'() {
        given:
        def product = new Product(id: 1l, name: 'product', brand: 'product', type: 'product', price: 1.0, quantity: 1)

        when:
        productService.saveProduct(product, 1l)

        then:
        1 * categoryService.findCategoryById(1l)
        1 * productRepository.save(product)

    }

    def 'test should find product by id'() {
        when:
        productService.findProductById(2l)

        then:
        1 * productRepository.getOne(2l)

    }

    def 'test updateProduct'() {
        given:
        def product = new Product(name: 'product', brand: 'product', type: 'product', price: 1.0, quantity: 1)
        productRepository.getOne(2L) >> new Product(id: 2L, name: 'product', brand: 'product', type: 'product', price: 1.0, quantity: 20)

        categoryService.findCategoryById(1L) >> new Category()

        when:
        def result = productService.updateProduct(product, 2L, 1L)

        then:
        result.id == 2L
        result.name == product.name
        result.brand == product.brand
        result.type == product.type
        result.price == product.price
        result.quantity == product.quantity

    }

    //ok
    def 'test getProductByCategory'() {
        when:
        productService.getProductByCategory(2l)

        then:
        1 * productRepository.findByCategoryId(2l)
        0 * _

    }
}
