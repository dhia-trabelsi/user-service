package com.pfe.rembursement;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.pfe.Act.Act;
import com.pfe.Act.ActRepository;
import com.pfe.Assurance.AssuranceRepository;
import com.pfe.Bds.Bulltin;
import com.pfe.Bds.BulltinRepository;
import com.pfe.Borderau.Borderau;
import com.pfe.Borderau.BorderauRepository;
import com.pfe.Societe.SocieteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RembursementService {

    private final RembursementRepository rembursementRepository;
    private final ActRepository actRepository;
    private final SocieteRepository societeRepository;
    private final AssuranceRepository assuranceRepository;
    private final BulltinRepository bulltinRepository;
    private final BorderauRepository borderauRepository;
    RestTemplate restTemplate = new RestTemplate();

    String url = "http://localhost:8082/api/user/plafond/";
    String url2 = "http://localhost:8082/api/user/soc/";
    String url3 = "http://localhost:8082/api/user/setPlafond";

    public rembursement save(rembursement rembursement) {
        return rembursementRepository.save(rembursement);
    }

    public rembursement getById(int id) {
        return rembursementRepository.findById(id).orElseThrow(() -> new RuntimeException("Rembursement not found"));
    }


    

    public rembursement rembourser(rembursement existingRembursement) {
        // rembursement existingRembursement = rembursementRepository.findById(id)
        //         .orElseThrow(() -> new RuntimeException("Rembursement not found"));

        System.out.println(existingRembursement);

        Bulltin bulltin = bulltinRepository.findById(existingRembursement.getBulltinId())
                .orElseThrow(() -> new RuntimeException("Bulletin not found"));
        Integer userId = bulltinRepository.findById(existingRembursement.getBulltinId()).get().getUserID();
        Borderau borderau = bulltin.getBorderau();


        bulltin.setMtt(bulltin.getMtt() + existingRembursement.getMtt());
        borderau.setMNet(borderau.getMNet() + existingRembursement.getMtt());
        Double plafond = restTemplate.getForObject(url + userId, Double.class);
        Integer societeId = restTemplate.getForObject(url2 + userId, Integer.class);

        Long assur = societeRepository.findById(societeId).get().getAssurance();
        Double plafonAssur = assuranceRepository.findById(assur).get().getPlaf_Mut();

        if (plafond > plafonAssur) {
            throw new RuntimeException("Plafond dépassé");
        }

        Act act = actRepository.findById(existingRembursement.getActId())
                .orElseThrow(() -> new RuntimeException("Act not found"));

        Double montant = 0.0;

        switch (act.getTypeAct().getLib()) {
            case "Consultation":
                montant = consultation();
                break;
            case "Pharmacie":
                montant = pharmacie(act, existingRembursement);
                break;
            case "Optique":
                montant = optique(act, existingRembursement);
                break;
            case "Infermier à Domicile":
                montant = Infermier(act, existingRembursement);
                break;
            case "Chirurgie":
                montant = Infermier(act, existingRembursement);
                break;
            case "Hospitalisation":
                montant = Hospitalisation(act, existingRembursement);
                break;
        }

         existingRembursement.setMttRemb(montant);
         bulltin.setMttRemb(bulltin.getMttRemb() + montant);
            borderau.setMHonor(borderau.getMHonor() + montant);
            bulltinRepository.save(bulltin);
            borderauRepository.save(borderau);
         rembursementRepository.save(existingRembursement);

        // update user plafond with restTemplate
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url3)
                .queryParam("plafond", montant)
                .queryParam("id", userId);
        // console debug
        System.out.println(builder.toUriString());
        restTemplate.exchange(builder.toUriString(), HttpMethod.POST, requestEntity, Void.class);

        return existingRembursement;


    }

    public Double consultation() {
        return 30.0;
    }

    public Double pharmacie(Act act, rembursement rembursement) {
        return (rembursement.getMtt() * act.getTaux()) / 100;
    }

    public Double optique(Act act, rembursement rembursement) {
        return (rembursement.getMtt() * act.getTaux()) / 100;
    }

    public Double Infermier(Act act, rembursement rembursement) {
        return (rembursement.getMtt() * act.getTaux()) / 100;
    }

    public Double Chirurgie(Act act, rembursement rembursement) {
        return (rembursement.getMtt() * act.getTaux()) / 100;
    }
    public Double Hospitalisation(Act act, rembursement rembursement) {
        return (rembursement.getMtt() * act.getTaux()) / 100;
    }

    @Transactional
    public void deleteAllByBulletinId(Integer id) {
        rembursementRepository.deleteAllByBulltinId(id);
       }

       public void delete(Integer id){
        rembursementRepository.deleteById(id);
       }

       public List<rembursement> getAllByBulletinId(Integer id){
        return rembursementRepository.findAllByBulltinId(id);
       }

    public List<rembursement> getAll() {
        return rembursementRepository.findAll();
    }


}
