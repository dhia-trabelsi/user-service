package com.pfe.Borderau;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<Double,Double> getStatistique(int id) {
        Map<Double,Double> map = new HashMap<>();
        List<Borderau> borderaus = borderauRepository.findBySocieteId(id);
        Double total = 0.0;
        Double totalH = 0.0;
        for (Borderau borderau : borderaus) {
            total += borderau.getMNet();
            totalH += borderau.getMHonor();
        }
        map.put(total, totalH);
        return map;
    }

    public List<Map<Double, Double>> getAllStatistique() {
        List<Map<Double, Double>> list = new java.util.ArrayList<>()
        ;
        List<Borderau> borderaus = borderauRepository.findAll();
        for (Borderau borderau : borderaus) {
            list.add(getStatistique(borderau.getSocieteId()));
        }
        return list;
        

    }

    public Borderau envoyer(Long id) {
        Borderau borderau = borderauRepository.findById(id).get();
        borderau.setEnvoyer(true);
        return borderauRepository.save(borderau);
    }

    public Borderau valider(Long id) {
        Borderau borderau = borderauRepository.findById(id).get();
        borderau.setValider(true);
        return borderauRepository.save(borderau);
    }


    


}
