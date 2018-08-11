package com.comodo.vaa25.api;

import com.comodo.vaa25.AbstractIntegrationTest;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.Test;

import static com.comodo.vaa25.ResourceReader.resourceAsString;
import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT_UNORDERED;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DatabaseSetup("/dbunit/empty.xml")
@DatabaseSetup("/dbunit/UserControllerTest/UserControllerTest.xml")
public class UserControllerTest extends AbstractIntegrationTest {

    @Test
    public void getUsersPage() throws Exception {
        this.mockMvc
                .perform(get("/users")
                        .param("page", "1")
                        .param("size", "1")
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().json(resourceAsString("/json/UserControllerTest/getUsersPage.json"), false))
        ;
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/UserControllerTest/addNewUser.xml", assertionMode = NON_STRICT_UNORDERED)
    public void addNewUser() throws Exception {
        this.mockMvc
                .perform(post("/users")
                        .content(resourceAsString("/json/UserControllerTest/addNewUser.json"))
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/UserControllerTest/editUser.xml", assertionMode = NON_STRICT_UNORDERED)
    public void editUser() throws Exception {
        this.mockMvc
                .perform(put("/users")
                        .content(resourceAsString("/json/UserControllerTest/editUser.json"))
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/UserControllerTest/deleteUser.xml", assertionMode = NON_STRICT_UNORDERED)
    public void deleteUser() throws Exception {
        this.mockMvc
                .perform(delete("/users")
                        .content(resourceAsString("/json/UserControllerTest/deleteUser.json"))
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}