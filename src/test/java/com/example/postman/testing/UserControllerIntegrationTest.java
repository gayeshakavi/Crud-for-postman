package com.example.postman.testing;

import com.example.postman.testing.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateAndGetUser() throws Exception {
        UserDTO userDTO = new UserDTO(100, "Integration Test User");

        // Create user
        MvcResult result = mockMvc.perform(post("/api/v1/users/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andReturn();

        // Get user by ID
        mockMvc.perform(get("/api/v1/users/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(100))
                .andExpect(jsonPath("$.name").value("Integration Test User"));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/api/v1/users/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}