package com.example.postman.testing;

import com.example.postman.testing.controller.UserController;
import com.example.postman.testing.dto.UserDTO;
import com.example.postman.testing.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO(1, "John Doe");
    }

    @Test
    public void testSaveUser() {
        // Arrange
        when(userService.saveUser(any(UserDTO.class))).thenReturn(userDTO);

        // Act
        ResponseEntity<UserDTO> response = userController.saveUser(userDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
        verify(userService, times(1)).saveUser(userDTO);
    }

    @Test
    public void testGetAllUsers() {
        // Arrange
        List<UserDTO> users = Arrays.asList(
                new UserDTO(1, "John Doe"),
                new UserDTO(2, "Jane Smith")
        );
        when(userService.getAllUsers()).thenReturn(users);

        // Act
        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testGetUserById_Found() {
        // Arrange
        when(userService.getUserById(1)).thenReturn(userDTO);

        // Act
        ResponseEntity<UserDTO> response = userController.getUserById(1);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
        verify(userService, times(1)).getUserById(1);
    }

    @Test
    public void testGetUserById_NotFound() {
        // Arrange
        when(userService.getUserById(999)).thenReturn(null);

        // Act
        ResponseEntity<UserDTO> response = userController.getUserById(999);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).getUserById(999);
    }

    @Test
    public void testUpdateUser() {
        // Arrange
        when(userService.updateUser(any(UserDTO.class))).thenReturn(userDTO);

        // Act
        ResponseEntity<UserDTO> response = userController.updateUser(userDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
        verify(userService, times(1)).updateUser(userDTO);
    }

    @Test
    public void testDeleteUser() {
        // Arrange
        when(userService.deleteUser(1)).thenReturn("User deleted with id: 1");

        // Act
        ResponseEntity<String> response = userController.deleteUser(1);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User deleted with id: 1", response.getBody());
        verify(userService, times(1)).deleteUser(1);
    }
}
