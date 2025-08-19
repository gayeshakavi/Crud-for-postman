package com.example.postman.testing.dto;

public class UserDTO {
    private int id;
    private String name;

    // Manually added constructors
    public UserDTO() {}  // No-args constructor

    public UserDTO(int id, String name) {  // All-args constructor
        this.id = id;
        this.name = name;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}