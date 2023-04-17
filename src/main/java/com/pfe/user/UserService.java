package com.pfe.user;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService{

    
    private final UserRepository repository;
    

    public List<UserDTO> getAllUsersWithRole(Role role) {
        List<User> users =  repository.findAllByRole(role);
        List<UserDTO> usersDTO = users.stream()
                                    .map(this::convertToDTO)
                                        .collect(Collectors.toList());
                        return usersDTO;
    }

    public UserDTO getUserByEmail(String email) {
        User user = repository.findByEmail(email).orElseThrow();
        UserDTO userDTO = convertToDTO(user);
        return userDTO;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = repository.findAll();
        List<UserDTO> usersDTO = users.stream()
                                    .map(this::convertToDTO)
                                        .collect(Collectors.toList());
                        return usersDTO;
    }

    public UserDTO getUserById(Integer id) {
         User user = repository.findById(id).orElseThrow();
         UserDTO userDTO = convertToDTO(user);
            return userDTO;
    }



  
    
    private UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .cin(user.getCin())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .sexe(user.getSexe())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .age(user.getAge())
                .coinjoint(user.getCoinjoint())
                .role(user.getRole())
                .build();
}


}
