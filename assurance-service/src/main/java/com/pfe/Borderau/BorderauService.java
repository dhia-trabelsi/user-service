package com.pfe.Borderau;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pfe.util.NotifRequest;
import com.pfe.util.NotifSender;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BorderauService {
    
    private final BorderauRepository borderauRepository;
    private final NotifSender notifSender;

    LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/api/user/roleID?role=ROLE_SUPER_ADMIN";

    public Borderau prepareBorderau(Borderau borderau) {
        
        ResponseEntity<List<Integer>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Integer>>() {
            });

    List<Integer> userIdList = response.getBody();
    NotifRequest notifRequest = new NotifRequest();
    notifRequest.setType("NEW_ACT");
    notifRequest.setDate(date);
    notifRequest.setMessage(
            "L'admin de la societe " + borderau.getSocieteId() + " a ajouter un nouveau borderau");
    for (Integer userId : userIdList) {
        notifRequest.setUser(userId);
        notifSender.sendNotif(notifRequest);
    }

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
        borderau.setEnvoyer("true");
        return borderauRepository.save(borderau);
    }

    public Borderau valider(Long id) {
        Borderau borderau = borderauRepository.findById(id).get();
        borderau.setValider("true");
        return borderauRepository.save(borderau);
    }


    


}
