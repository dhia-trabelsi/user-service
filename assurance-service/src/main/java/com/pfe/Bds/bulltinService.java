package com.pfe.Bds;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pfe.Borderau.Borderau;
import com.pfe.Borderau.BorderauRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class bulltinService {


    private final BulltinRepository bulltinRepository;
    private final BorderauRepository borderauRepository;


    public Bulltin saveBulltin(Bulltin bulltin, long code) {
       Borderau b = borderauRepository.findById(code).orElseThrow(() -> new RuntimeException("Borderau not found"));
        
        bulltin.setBorderau(b);
        return bulltinRepository.save(bulltin);
    }

    public Bulltin getById(int id) {
        return bulltinRepository.findById(id).orElseThrow(() -> new RuntimeException("Bulltin not found"));
    }

    public List<Bulltin> getAll() {
        return bulltinRepository.findAll();
    }

    public void delete(int id) {
        bulltinRepository.deleteById(id);
    }


 
}
