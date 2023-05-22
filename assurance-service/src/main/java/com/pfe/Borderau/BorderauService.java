package com.pfe.Borderau;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BorderauService {
    
    private final BorderauRepository borderauRepository;

    public Borderau prepareBorderau(Borderau borderau) {
        

        return borderauRepository.save(borderau);
    }

    public Borderau getById(Long id) {
        return borderauRepository.findById(id).orElseThrow(() -> new RuntimeException("Borderau not found"));
    }

    public void delete(Long id) {
        borderauRepository.deleteById(id);
    }

    public List<Borderau> getAll() {
        return borderauRepository.findAll();
    }


    


}
