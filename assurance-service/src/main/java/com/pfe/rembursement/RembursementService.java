package com.pfe.rembursement;

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
import com.pfe.Societe.SocieteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RembursementService {

    private final RembursementRepository rembursementRepository;
    private final ActRepository actRepository;
    private final SocieteRepository societeRepository;
    private final AssuranceRepository assuranceRepository;
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

    public void delete(int id) {
        rembursementRepository.deleteById(id);
    }

    // public rembursement rembourser(rembursement rembursement, Integer id) {

    // Integer userId =
    // rembursementRepository.findById(id).get().getBulltin().getUserID();

    // Double plafond = restTemplate.getForObject(url + userId, Double.class);
    // Integer societeId = restTemplate.getForObject(url2 + userId, Integer.class);

    // Long Assur = societeRepository.findById(societeId).get().getAssurance();
    // Double plafonAssur = assuranceRepository.findById(Assur).get().getPlaf_Mut();

    // if (plafond > plafonAssur) {
    // throw new RuntimeException("Plafond dépassé");
    // }

    // Act act = actRepository.findById(rembursement.getActId())
    // .orElseThrow(() -> new RuntimeException("Act not found"));
    // Double montant = 0.0;

    // switch (act.getTypeAct().getLib()) {
    // case "Consultation":

    // montant = consultation();
    // break;
    // case "Pharmacie":

    // montant = pharmacie(act, rembursement);
    // break;
    // case "Optique":
    // montant = optique(act, rembursement);
    // break;
    // }

    // rembursement.setMttRemb(montant);

    // HttpHeaders headers = new HttpHeaders();
    // headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    // // Create the request entity with headers and request parameters
    // HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

    // // Set the request parameters
    // UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url3)
    // .queryParam("plafond", plafond)
    // .queryParam("id", userId);

    // System.out.println(builder.toUriString());
    // // Send the POST request
    // restTemplate.exchange(builder.toUriString(), HttpMethod.POST, requestEntity,
    // Void.class);

    // return rembursementRepository.save(rembursement);
    // }

    public rembursement rembourser(Integer id) {
        rembursement existingRembursement = rembursementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rembursement not found"));

        Integer userId = existingRembursement.getBulltin().getUserID();

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

        rembursementRepository.save(existingRembursement);

        // update user plafond with restTemplate
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url3)
                .queryParam("plafond", plafond)
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
}
