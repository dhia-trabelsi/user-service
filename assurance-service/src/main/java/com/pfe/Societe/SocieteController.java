package com.pfe.Societe;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/societe")
public class SocieteController {

 private final SocieteService societeService;

    @PostMapping
    public Societe save(@RequestBody Societe societe) {
    return societeService.CreateSociete(societe);
    }

    @GetMapping("/{id}")
    public Societe getById(@PathVariable int id) {
        return societeService.getById(id);
    }

    @GetMapping("/all")
    public List<Societe> getAll() {
        return societeService.getAll();
    }

   


    
}
