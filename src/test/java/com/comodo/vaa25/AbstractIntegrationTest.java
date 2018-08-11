package com.comodo.vaa25;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Abstract class for integration tests.
 */
@AutoConfigureMockMvc
public class AbstractIntegrationTest extends AbstractDbunitTest {

    @Autowired
    protected MockMvc mockMvc;

}
