package com.comodo.vaa25.api;

import com.comodo.vaa25.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.comodo.vaa25.ResourceReader.resourceAsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void tryEditUserWithEmptyFirstName() throws Exception {
        final String message = this.mockMvc
                .perform(put("/users/-2")
                        .content(resourceAsString("/json/UserControllerValidationTest/tryEditUserWithEmptyFirstName.json"))
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException().getMessage();
        verify(userService, never()).editUser(any(), any());
        assertThat(message, new Contains("First name size should be between 1 and 30 symbols"));
    }

    @Test
    public void tryEditUserWithInvalidId() throws Exception {
        this.mockMvc
                .perform(put("/users/df")
                        .content(resourceAsString("/json/UserControllerValidationTest/tryEditUserWithInvalidId.json"))
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("\"df\" should be the number"));
        verify(userService, never()).editUser(any(), any());
    }

    @Test
    public void tryCreateUserWithEmptyLastName() throws Exception {
        final String message = this.mockMvc
                .perform(post("/users")
                        .content(resourceAsString("/json/UserControllerValidationTest/tryEditUserWithEmptyLastName.json"))
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException().getMessage();
        verify(userService, never()).createUser(any());
        assertThat(message, new Contains("Last name size should be between 1 and 30 symbols"));
    }

    @Test
    public void tryCreateUserWithWrongGender() throws Exception {
        this.mockMvc
                .perform(post("/users")
                        .content(resourceAsString("/json/UserControllerValidationTest/tryCreateUserWithWrongGender.json"))
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
        verify(userService, never()).createUser(any());
    }

    @Test
    public void tryCreateUserWithBirthDayInFuture() throws Exception {
        final String message = this.mockMvc
                .perform(post("/users")
                        .content(resourceAsString("/json/UserControllerValidationTest/tryCreateUserWithBirthDayInFuture.json"))
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException().getMessage();
        verify(userService, never()).createUser(any());
        assertThat(message, new Contains("Birth day should be in the past"));
    }

}