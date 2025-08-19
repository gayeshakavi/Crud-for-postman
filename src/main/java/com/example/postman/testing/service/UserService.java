package com.example.postman.testing.service;

import com.example.postman.testing.dto.UserDTO;
import com.example.postman.testing.model.User;
import com.example.postman.testing.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;

    // Save User
    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getId(),
                userDTO.getName()
        );
        userRepo.save(user);
        return userDTO;
    }

    // Get All Users
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(user -> new UserDTO(user.getId(), user.getName()))
                .collect(Collectors.toList());
    }

    // Get User by ID
    public UserDTO getUserById(int id) {
        return userRepo.findById(id)
                .map(user -> new UserDTO(user.getId(), user.getName()))
                .orElse(null);
    }

    // Update User
    public UserDTO updateUser(UserDTO userDTO) {
        User user = new User(userDTO.getId(), userDTO.getName());
        userRepo.save(user);
        return userDTO;
    }

    // Delete User
    public String deleteUser(int id) {
        userRepo.deleteById(id);
        return "User deleted with id: " + id;
    }

}