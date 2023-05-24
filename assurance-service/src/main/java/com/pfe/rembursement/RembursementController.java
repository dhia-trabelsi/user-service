package com.pfe.rembursement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rembursement")
@RequiredArgsConstructor
public class RembursementController {
    

    private final RembursementService rembursementService;

    @PostMapping
    public rembursement save(@RequestBody rembursement rembursement) {
        return rembursementService.save(rembursement);
    }

    @PostMapping("/rembourser/{id}")
    public ResponseEntity<rembursement> rembourser( @PathVariable Integer id) {
        return ResponseEntity.ok(rembursementService.rembourser(id));
    }
}
