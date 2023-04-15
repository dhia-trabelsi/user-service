package com.pfe.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    


    private final UserService userService;
    
   
  
    @GetMapping("/role")
    public ResponseEntity<?> getAllUsersWithRole(@RequestParam Role role) {
        
        return ResponseEntity.ok(userService.getAllUsersWithRole(role));

    }


    @GetMapping("/email")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        
        return ResponseEntity.ok(userService.getUserByEmail(email));

    }

    

}
