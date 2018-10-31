package com.nbohn.pundit.rest.controller;

import com.nbohn.pundit.rest.service.PunService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Pun Controller Test
 */
@RunWith(SpringRunner.class)                                // Use JUnit Spring Test Runner.
@WebMvcTest(value = PunController.class, secure = false)    // Identify this test class as a Spring Web MVC Test.
public class PunControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PunService punService;

    @Test
    public void testList() {

    }
}
