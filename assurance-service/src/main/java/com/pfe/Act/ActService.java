package com.pfe.Act;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pfe.TypeAct.TypeAct;
import com.pfe.TypeAct.TypeActRepository;
import com.pfe.util.HistoryRequest;
import com.pfe.util.HistorySender;
import com.pfe.util.NotifRequest;
import com.pfe.util.NotifSender;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActService {

        private final ActRepository actRepository;
        private final TypeActRepository typeActRepository;
        private final NotifSender notifSender;
        private final HistorySender historySender;
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/api/user/roleID?role=ROLE_ADMIN";

        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

    public Act save(ActDTO act) {

        TypeAct existingTypeAct = typeActRepository.findById(act.getTypeAct().getTypeAct())
                .orElseThrow(() -> new RuntimeException("TypeAct instance not found in the database"));
        Act newAct = Act.builder()
                .abrv(act.getAbrv())
                .lib(act.getLib())
                .date(act.getDate())
                .plafonne(act.getPlafonne())
                .ctr(act.getCtr())
                .sexe(act.getSexe())
                .parent(act.getParent())
                .typeAct(act.getTypeAct())
                .build();

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
                "nouveau acte médical : " + act.getLib() + " est ajouté au type : " + existingTypeAct.getLib());
        for (Integer userId : userIdList) {
            notifRequest.setUser(userId);
            notifSender.sendNotif(notifRequest);
        }

        HistoryRequest historyRequest = new HistoryRequest();
        historyRequest.setMessage(
                "nouveau acte médical : " + act.getLib() + " est ajouté au type : " + existingTypeAct.getLib() + " à " + date);
        historyRequest.setDate(date);
        historyRequest.setType("ACT");
        historySender.sendHistory(historyRequest);


        return actRepository.save(newAct);

    }

    public Act getById(int id) {
        return actRepository.findById(id).orElseThrow();
    }

    public List<Act> getAll() {
        return actRepository.findAll();
    }

    public void delete(int id) {
        actRepository.deleteById(id);
        ResponseEntity<List<Integer>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Integer>>() {
                });
        List<Integer> userIdList = response.getBody();

        NotifRequest notifRequest = new NotifRequest();
        notifRequest.setType("DELETE_ACT");
        notifRequest.setDate(date);
        notifRequest.setMessage("acte médical : " + id + " est supprimé");
        for (Integer userId : userIdList) {
            notifRequest.setUser(userId);
            notifSender.sendNotif(notifRequest);
        }
        HistoryRequest historyRequest = new HistoryRequest();
        historyRequest.setMessage("acte médical : " + id + " est supprimé à " + date);
        historyRequest.setDate(date);
        historyRequest.setType("ACT");
        historySender.sendHistory(historyRequest);
    }

    public Act update(int id, Act act) {

        Act actToUpdate = actRepository.findById(id).orElseThrow();
        actToUpdate.setAbrv(act.getAbrv());
        actToUpdate.setLib(act.getLib());
        actToUpdate.setDate(act.getDate());
        actToUpdate.setPlafonne(act.getPlafonne());
        actToUpdate.setCtr(act.getCtr());
        actToUpdate.setParent(act.getParent());
        actToUpdate.setTypeAct(act.getTypeAct());
        actToUpdate.setSexe(act.getSexe());
        actToUpdate.setDuree(act.getDuree());
        actToUpdate.setIndice(act.getIndice());
        actToUpdate.setMtt(act.getMtt());
        actToUpdate.setPlafond(act.getPlafond());
        actToUpdate.setPiece(act.getPiece());
        actToUpdate.setVign(act.getVign());
        actToUpdate.setTaux(act.getTaux());

        ResponseEntity<List<Integer>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Integer>>() {
                });
        List<Integer> userIdList = response.getBody();

        NotifRequest notifRequest = new NotifRequest();
        notifRequest.setType("UPDATE_ACT");
        notifRequest.setDate(date);
        notifRequest.setMessage("acte médical : " + act.getLib() + " est modifié");
        for (Integer userId : userIdList) {
            notifRequest.setUser(userId);
            notifSender.sendNotif(notifRequest);
        }

        HistoryRequest historyRequest = new HistoryRequest();
        historyRequest.setMessage("acte médical : " + act.getLib() + " est modifié à " + date);
        historyRequest.setDate(date);
        historyRequest.setType("ACT");
        historySender.sendHistory(historyRequest);

        return actRepository.save(actToUpdate);
    }

    public Act getByAbrv(String abrv) {
        return actRepository.findByAbrv(abrv);
    }
}
