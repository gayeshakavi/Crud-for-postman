package com.example.postman.testing;

import com.example.postman.testing.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserTest {
    @Test
    public void testUserConstructorAndGetters() {
        // Arrange & Act
        User user = new User(1, "John Doe");

        // Assert
        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getName());
    }

    @Test
    public void testUserSetters() {
        // Arrange
        User user = new User();

        // Act
        user.setId(2);
        user.setName("Jane Smith");

        // Assert
        assertEquals(2, user.getId());
        assertEquals("Jane Smith", user.getName());
    }
}
