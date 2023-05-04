package com.pfe.Borderau;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BorderauService {
    
    private final BorderauRepository borderauRepository;

    public Borderau save(Borderau borderau) {
        
        return borderauRepository.save(borderau);
    }

}
