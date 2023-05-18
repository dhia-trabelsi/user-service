package com.notification.History;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
public class HistoryController {

    private final HistoryService historyService;
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody History history) {
        return ResponseEntity.ok(historyService.save(history));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(historyService.findAll());
    }

    @GetMapping("/type")
    public ResponseEntity<?> findBytype(@RequestParam("type") HistoryType type) {
        return ResponseEntity.ok(historyService.findBytype(type));
    }
}
