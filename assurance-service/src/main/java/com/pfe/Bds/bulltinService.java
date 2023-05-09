package com.pfe.Bds;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class bulltinService {


    private final BulltinRepository bulltinRepository;


    public Bulltin saveBulltin(Bulltin bulltin) {
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
