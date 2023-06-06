package com.pfe.Bds;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bulltin")
public class BulltinController {
    
    private final bulltinService bulltinService;


    @PostMapping
    public ResponseEntity<Bulltin> saveBulltin(@RequestBody Bulltin bulltin,@RequestParam("code") long code) {
        return ResponseEntity.ok(bulltinService.saveBulltin(bulltin,code));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bulltin> getById(@PathVariable int id) {
        return ResponseEntity.ok(bulltinService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(bulltinService.getAll());
    }

    @PostMapping("/image/{id}")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable Integer id) throws IOException {
        return ResponseEntity.ok(bulltinService.uploadImageToFileSystem(file, id));
    }

    @PostMapping("/valider/{id}")
    public ResponseEntity<?> valider(@PathVariable int id) {
        return ResponseEntity.ok(bulltinService.valider(id));
    }

    @PostMapping("/refuser/{id}")
    public ResponseEntity<?> refuser(@PathVariable int id) {
        return ResponseEntity.ok(bulltinService.refuser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bulltin> updateBulltin(@RequestBody Bulltin bulltin, @PathVariable int id) {
        return ResponseEntity.ok(bulltinService.updateBulltin(bulltin, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        bulltinService.delete(id);
        return ResponseEntity.ok().build();
    }

    

    
}
