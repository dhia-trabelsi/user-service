package com.pfe.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        
        if(userService.getAllUsersWithRole(role) == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(userService.getAllUsersWithRole(role));
        }
    }


    @GetMapping("/email")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        
        if(userService.getUserByEmail(email) == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(userService.getUserByEmail(email));
        }

    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        if(userService.getAllUsers() == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(userService.getAllUsers());
        }
    }
  
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserByid(@PathVariable Integer id) {
        
        if(userService.getUserById(id) == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok(userService.getUserById(id));
        }
        
    }



    

}
