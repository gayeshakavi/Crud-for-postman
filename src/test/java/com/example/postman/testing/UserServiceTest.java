package com.example.postman.testing;

import com.example.postman.testing.dto.UserDTO;
import com.example.postman.testing.model.User;
import com.example.postman.testing.repo.UserRepo;
import com.example.postman.testing.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    private UserDTO userDTO;
    private User user;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO(1, "John Doe");
        user = new User(1, "John Doe");
    }

    @Test
    public void testSaveUser() {
        // Arrange
        when(userRepo.save(any(User.class))).thenReturn(user);

        // Act
        UserDTO result = userService.saveUser(userDTO);

        // Assert
        assertNotNull(result);
        assertEquals(userDTO.getId(), result.getId());
        assertEquals(userDTO.getName(), result.getName());
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    public void testGetAllUsers() {
        // Arrange
        List<User> users = Arrays.asList(
                new User(1, "John Doe"),
                new User(2, "Jane Smith")
        );
        when(userRepo.findAll()).thenReturn(users);

        // Act
        List<UserDTO> result = userService.getAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userRepo, times(1)).findAll();
    }

    @Test
    public void testGetUserById_Found() {
        // Arrange
        when(userRepo.findById(1)).thenReturn(Optional.of(user));

        // Act
        UserDTO result = userService.getUserById(1);

        // Assert
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getName(), result.getName());
        verify(userRepo, times(1)).findById(1);
    }

    @Test
    public void testGetUserById_NotFound() {
        // Arrange
        when(userRepo.findById(999)).thenReturn(Optional.empty());

        // Act
        UserDTO result = userService.getUserById(999);

        // Assert
        assertNull(result);
        verify(userRepo, times(1)).findById(999);
    }

    @Test
    public void testUpdateUser() {
        // Arrange
        when(userRepo.save(any(User.class))).thenReturn(user);

        // Act
        UserDTO result = userService.updateUser(userDTO);

        // Assert
        assertNotNull(result);
        assertEquals(userDTO.getId(), result.getId());
        assertEquals(userDTO.getName(), result.getName());
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    public void testDeleteUser() {
        // Arrange
        doNothing().when(userRepo).deleteById(1);

        // Act
        String result = userService.deleteUser(1);

        // Assert
        assertEquals("User deleted with id: 1", result);
        verify(userRepo, times(1)).deleteById(1);
    }
}
