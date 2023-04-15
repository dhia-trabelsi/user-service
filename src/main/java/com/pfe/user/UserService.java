package com.pfe.user;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository repository;
    

    public List<User> getAllUsersWithRole(Role role) {
        return repository.findAllByRole(role);
    }

    public User getUserByEmail(String email) {
        return repository.findByEmail(email).orElseThrow();
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(Integer id) {
        return repository.findById(id).orElseThrow();
    }
}
