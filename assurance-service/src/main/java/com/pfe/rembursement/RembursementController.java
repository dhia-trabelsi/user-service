package com.pfe.rembursement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    @PostMapping("/rembourser")
    public ResponseEntity<rembursement> rembourser(@RequestBody rembursement rembursement) {
        return ResponseEntity.ok(rembursementService.rembourser(rembursement));
    }

    @GetMapping("/getById")
    public ResponseEntity<rembursement> getById(int id) {
        return ResponseEntity.ok(rembursementService.getById(id));
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        rembursementService.delete(id);
    }

    @DeleteMapping("/deleteAllByBulltinId/{id}")
    public void deleteAllByBulltinId(@PathVariable Integer id) {
        rembursementService.deleteAllByBulletinId(id);
    }

    @GetMapping("/findAllByBulltinId/{id}")
    public ResponseEntity<?> findAllByBulltinId(@PathVariable Integer id) {
        return ResponseEntity.ok(rembursementService.getAllByBulletinId(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(rembursementService.getAll());
    }




}
