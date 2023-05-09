package com.pfe.Bds;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bulltin")
public class BulltinController {
    
    private final bulltinService bulltinService;


    @PostMapping
    public ResponseEntity<Bulltin> saveBulltin(Bulltin bulltin) {
        return ResponseEntity.ok(bulltinService.saveBulltin(bulltin));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bulltin> getById(@PathVariable int id) {
        return ResponseEntity.ok(bulltinService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(bulltinService.getAll());
    }
}
