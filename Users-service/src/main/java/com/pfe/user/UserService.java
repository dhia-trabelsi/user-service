package com.pfe.user;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.pfe.Password.passwordService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final passwordService service;
    private final ChildRepository childRepository;

    public List<UserDTO> getAllUsersWithRole(Role role) {
        List<User> users = repository.findAllByRole(role);
        List<UserDTO> usersDTO = users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return usersDTO;
    }

    public List<Integer> getUsersIdWithRole(Role role) {
        List<User> users = repository.findAllByRole(role);
        List<Integer> usersId = users.stream()
                .map(User::getId)
                .collect(Collectors.toList());
        return usersId;
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

    public void deleteUserById(Integer id) {

        repository.deleteById(id);
    }

    public User updateUser(User updatedUser, Integer id) {
        User existingUser = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    
        if (updatedUser.getFirstname() != null) {
            existingUser.setFirstname(updatedUser.getFirstname());
        }
        if (updatedUser.getLastname() != null) {
            existingUser.setLastname(updatedUser.getLastname());
        }
        if (updatedUser.getCin() != null) {
            existingUser.setCin(updatedUser.getCin());
        }
        if (updatedUser.getSexe() != null) {
            existingUser.setSexe(updatedUser.getSexe());
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPhone() != null) {
            existingUser.setPhone(updatedUser.getPhone());
        }
        if (updatedUser.getAddress() != null) {
            existingUser.setAddress(updatedUser.getAddress());
        }
        if (updatedUser.getAge() != null) {
            existingUser.setAge(updatedUser.getAge());
        }
        if (updatedUser.getCoinjoint() != null) {
            existingUser.setCoinjoint(updatedUser.getCoinjoint());
        }
        return repository.save(existingUser);
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
                .societeId(user.getSocieteId())
                .filepath(user.getFilepath())
                .build();
    }

    public List<UserDTO> getUsersBySocieteID(Integer societeId, Role role) {
        List<User> users = repository.findAllBySocieteIdAndRole(societeId, role);
        List<UserDTO> usersDTO = users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return usersDTO;
    
    }

    public Role getUserByRole(String email) {
        User user = repository.findByEmail(email).orElseThrow();
        Role role = user.getRole();
        return role;
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return (User) principal;
        } else {
            return null;
        }
    }

    private final String FOLDER_PATH = "C:/Users/trabe/Desktop/MyFIles/";

    public String uploadImageToFileSystem(MultipartFile file, int id) throws IOException {

        User user = repository.findById(id).orElseThrow();
        String filePath = FOLDER_PATH + file.getOriginalFilename();

        user.setFilepath(filePath);
        repository.save(user);

        file.transferTo(new File(filePath));

        return "image uploaded successfully...";
    }


    public Integer getSocieteId(Integer id) {
        User user = repository.findById(id).orElseThrow();
        return user.getSocieteId();
    }
    

    public void createPasswordResetTokenForUser(User user, String passwordResetToken) {
        service.createPasswordResetTokenForUser(user, passwordResetToken);
    }

    public String validatePasswordResetToken(String passwordResetRequest) {
        return service.validatePasswordResetToken(passwordResetRequest);
    }

    public User findUserByPasswordToken(String token) {
        return service.findUserByPasswordToken(token).get();
    }

    public void resetPassword(User user, String newPassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(newPassword));
        repository.save(user);
    }

    public Object getUserIdByEmail(String email) {
        User user = repository.findByEmail(email).orElseThrow();
        return user.getId();
    }

    // public List<Child> getChilds(Integer id) {
    //     User user = repository.findById(id).orElseThrow();

    //     return childRepository.findAllByUser(user);
    // }

    public Integer getBroker() {
        User user =  repository.findAllByRole(Role.ROLE_SUPER_ADMIN).get(0);
        return user.getId();
    }

    public Double getPlafon(Integer id){
        return repository.findById(id).get().getPlafond();

    }

    public void setPlafon(Double plafond, Integer id){
        User user = repository.findById(id).get();
        user.setPlafond(plafond + user.getPlafond());
        repository.save(user);
    }

    

}
