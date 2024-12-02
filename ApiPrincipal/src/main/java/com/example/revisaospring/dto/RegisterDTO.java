package com.example.revisaospring.dto;

import com.example.revisaospring.entities.Roles;

public record RegisterDTO(String email, String password, Roles role) {

}
