package com.comodo.vaa25.api;

import com.comodo.vaa25.AbstractDbunitTest;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static com.comodo.vaa25.ResourceReader.resourceAsString;
import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT_UNORDERED;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DatabaseSetup("/dbunit/empty.xml")
@DatabaseSetup("/dbunit/UserControllerTest/UserControllerTest.xml")
@AutoConfigureMockMvc
public class UserControllerTest extends AbstractDbunitTest {

    @Autowired
    protected MockMvc mockMvc;

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
    @ExpectedDatabase(value = "/dbunit/UserControllerTest/createsNewUser.xml", assertionMode = NON_STRICT_UNORDERED)
    public void createsNewUser() throws Exception {
        this.mockMvc
                .perform(post("/users")
                        .content(resourceAsString("/json/UserControllerTest/createsNewUser.json"))
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/UserControllerTest/editUser.xml", assertionMode = NON_STRICT_UNORDERED)
    public void editUser() throws Exception {
        this.mockMvc
                .perform(put("/users/-2")
                        .content(resourceAsString("/json/UserControllerTest/editUser.json"))
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/UserControllerTest/UserControllerTest.xml", assertionMode = NON_STRICT_UNORDERED)
    public void tryEditAbsentUser() throws Exception {
        this.mockMvc
                .perform(put("/users/-4")
                        .content(resourceAsString("/json/UserControllerTest/tryEditAbsentUser.json"))
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/UserControllerTest/deleteUser.xml", assertionMode = NON_STRICT_UNORDERED)
    public void deleteUser() throws Exception {
        this.mockMvc
                .perform(delete("/users/-2"))
                .andExpect(status().isOk());
    }

    @Test
    @ExpectedDatabase(value = "/dbunit/UserControllerTest/UserControllerTest.xml", assertionMode = NON_STRICT_UNORDERED)
    public void tryDeleteAbsentUser() throws Exception {
        this.mockMvc
                .perform(delete("/users/-4"))
                .andExpect(status().isBadRequest());
    }
}