package pl.damian.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import pl.damian.shop.domain.dao.Category;
import pl.damian.shop.domain.dao.Product;
import pl.damian.shop.domain.dto.ProductDto;
import pl.damian.shop.domain.dto.UserDto;
import pl.damian.shop.repository.CategoryRepository;
import pl.damian.shop.repository.ProductRepository;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void shouldNotSaveUserWithOutAdminPermission() throws Exception {
        mockMvc.perform(post("/api/products/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ProductDto.builder()
                        .brand("brand")
                        .name("name")
                        .price(20.)
                        .quantity(1)
                        .type("type")
                        .categoryId(1l)
                        .build())))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$").doesNotExist());

    }

    @WithMockUser(roles = ("ADMIN"))
    @Test
    void shouldNotSaveUserFieldValidation() throws Exception {
        mockMvc.perform(post("/api/products/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new UserDto())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    @WithMockUser(roles = ("ADMIN"))
    void shouldSaveProductWithAdminPermission() throws Exception {
        mockMvc.perform(post("/api/products/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ProductDto.builder()
                        .brand("brand")
                        .name("name")
                        .price(20.)
                        .quantity(1)
                        .type("type")
                        .categoryId(1l)
                        .build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void shouldGetProductByIdWhenIdDoNotExist() throws Exception {
        Product product = productRepository.save(Product.builder()
                .brand("brand")
                .name("name")
                .price(20.)
                .quantity(1)
                .type("type")
                .build());
        mockMvc.perform(get("/api/products/" + product.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(product.getName()))
                .andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.brand").value(product.getBrand()))
                .andExpect(jsonPath("$.price").value(product.getPrice()))
                .andExpect(jsonPath("$.quantity").value(product.getQuantity()))
                .andExpect(jsonPath("$.type").value(product.getType()));

    }

    @Test
    void shouldNotUpdateProductWithOutPermission() throws Exception {
        mockMvc.perform(put("/api/products/22")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ProductDto.builder()
                        .brand("brand")
                        .name("name")
                        .price(20.)
                        .quantity(1)
                        .type("type")
                        .build())))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$").doesNotExist());


    }

    ////////////////////////////////////////////
    @Test
    @WithMockUser(roles = ("ADMIN"))
    void shouldUpdateProductWithAdminPermission() throws Exception {
        Category category = categoryRepository.save(Category.builder()
                .name("category")
                .build());

        Product product = productRepository.save(Product.builder()
                .brand("brand")
                .name("name")
                .price(20.)
                .quantity(1)
                .type("type")
                .build());
        mockMvc.perform(put("/api/products/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ProductDto.builder()
                        .brand("brand")
                        .name("name")
                        .price(10.)
                        .quantity(1)
                        .type("type")
                        .categoryId(category.getId())
                        .build())))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.brand").value("brand"));///dokonczyc

    }


    @Test
    void shouldNotDeleteProductByIdWithOutAdminRole() throws Exception {
        mockMvc.perform(delete("/api/products/22"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void shouldDeleteUserByIdWithAdminRole() throws Exception {
        Product product = productRepository.save(Product.builder()
                .brand("brand")
                .name("name")
                .price(20.)
                .quantity(1)
                .type("type")
                .build());
        mockMvc.perform(delete("/api/products/" + product.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());

    }

}
