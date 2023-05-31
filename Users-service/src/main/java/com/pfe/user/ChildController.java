package com.pfe.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/child")
@RequiredArgsConstructor
public class ChildController {
    

    private final ChildService childService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Child child) {
        return ResponseEntity.ok(childService.save(child));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(childService.findById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        childService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Child child) {
        return ResponseEntity.ok(childService.update(child));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable Integer id) {
        return ResponseEntity.ok(childService.findByUserId(id));
    }


}
