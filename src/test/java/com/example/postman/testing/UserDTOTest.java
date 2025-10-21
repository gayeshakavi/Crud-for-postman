package com.example.postman.testing;

import com.example.postman.testing.dto.UserDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserDTOTest {
    @Test
    public void testUserDTOConstructorAndGetters() {
        // Arrange & Act
        UserDTO user = new UserDTO(1, "John Doe");

        // Assert
        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getName());
    }

    @Test
    public void testUserDTOSetters() {
        // Arrange
        UserDTO user = new UserDTO();

        // Act
        user.setId(2);
        user.setName("Jane Smith");

        // Assert
        assertEquals(2, user.getId());
        assertEquals("Jane Smith", user.getName());
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange & Act
        UserDTO user = new UserDTO();

        // Assert
        assertNotNull(user);
        assertEquals(0, user.getId());
        assertNull(user.getName());
    }
}
