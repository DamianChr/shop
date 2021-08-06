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
import pl.damian.shop.domain.dao.User;
import pl.damian.shop.domain.dto.UserDto;
import pl.damian.shop.repository.UserRepository;

import javax.transaction.Transactional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WithMockUser(roles = ("ADMIN"))
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldNotSaveUserFieldValidation() throws Exception {
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new UserDto())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").exists());

    }

    @Test
    void shouldNotSaveUserWithWrongPassword() throws Exception {
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UserDto.builder()
                        .firstName("firstName")
                        .lastName("lastName")
                        .password("password")
                        .email("email@wp.pl")
                        .mobile("111111")
                        .confirmPassword("qwerty")
                        .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist());


    }

    @Test
    void shouldNotGetUserByIdWithoutPermission() throws Exception {
        mockMvc.perform(get("/api/users/22"))
                .andExpect(status().isForbidden())
                .andExpect((jsonPath("$").doesNotExist()));
    }

    @WithMockUser(roles = ("ADMIN"))
    @Test
    void shouldGetUserByIdWithPermission() throws Exception {
        User user = userRepository.save(User.builder()
                .firstName("firstName")
                .lastName("lastName")
                .password("password")
                .email("email@wp.pl")
                .mobile("111111")
                .build());
        mockMvc.perform(get("/api/users/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.mobile").value(user.getMobile()));
    }


    @Test
    void shouldNotUpdateUserWithOutAdminPermission() throws Exception {
        mockMvc.perform(put("/api/users/22")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UserDto.builder()
                        .firstName("firstName")
                        .lastName("lastName")
                        .password("password")
                        .email("email@wp.pl")
                        .mobile("111111")
                        .confirmPassword("password")
                        .build())))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$").doesNotExist());

    }

    @WithMockUser(username = "email1@wp.pl")
    @Test
    void shouldNotUpdateUserWithoutAccessToUser() throws Exception {
        userRepository.save(User.builder()
                .firstName("firstName")
                .lastName("lastName")
                .password("password")
                .email("email1@wp.pl")
                .mobile("111111")
                .build());
        User user = userRepository.save(User.builder()
                .firstName("firstName")
                .lastName("lastName")
                .password("password")
                .email("email2@wp.pl")
                .mobile("111111")
                .build());
        mockMvc.perform(put("/api/users/" + user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UserDto.builder()
                        .firstName("firstName")
                        .lastName("lastName")
                        .password("password")
                        .email("email@wp.pl")
                        .mobile("111111")
                        .confirmPassword("password")
                        .build())))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$").doesNotExist());


    }

    @WithMockUser(username = "email2@wp.pl")
    @Test
    void shouldUpdateUserWithAccessToUser() throws Exception {
        User user = userRepository.save(User.builder()
                .firstName("firstName")
                .lastName("lastName")
                .password("password")
                .email("email2@wp.pl")
                .mobile("111111")
                .build());
        mockMvc.perform(put("/api/users/" + user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UserDto.builder()
                        .firstName("firstName")
                        .lastName("lastName")
                        .password("password")
                        .email("email2@wp.pl")
                        .mobile("111111")
                        .confirmPassword("password")
                        .build())))
                .andExpect(status().isOk());

    }


    @Test
    void shouldNotDeleteUserByIdWithOutAdminRole() throws Exception {
        mockMvc.perform(delete("/api/users/22"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void shouldDeleteUserByIdWithAdminRole() throws Exception {
        User user = userRepository.save(User.builder()
                .firstName("firstName")
                .lastName("lastName")
                .password("password")
                .email("email2@wp.pl")
                .mobile("111111")
                .build());
        mockMvc.perform(delete("/api/users/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());

    }

    @Test
    void shouldNotGetPageWithOutAdminRole() throws Exception {
        mockMvc.perform(get("/api/users/")
                .queryParam("page", "0")
                .queryParam("size", "0"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void shouldGetPageWithAdminRole() throws Exception {
        User user = userRepository.save(User.builder()
                .firstName("firstName")
                .lastName("lastName")
                .password("password")
                .email("email2@wp.pl")
                .mobile("111111")
                .build());
        mockMvc.perform(get("/api/users/")
                .queryParam("page", "0")
                .queryParam("size", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content", hasSize(1)));


    }

}
