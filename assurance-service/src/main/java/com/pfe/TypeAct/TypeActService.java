package com.pfe.TypeAct;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pfe.util.HistoryRequest;
import com.pfe.util.HistorySender;
import com.pfe.util.NotifRequest;
import com.pfe.util.NotifSender;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeActService {

    private final TypeActRepository typeActRepository;
    private final NotifSender notifSender;
    private final HistorySender historySender;

    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8082/api/user/roleID?role=ROLE_ADMIN";

    LocalDateTime localDateTime = LocalDateTime.now();
    Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

    public TypeAct save(TypeAct typeAct) {
        TypeAct newTypeAct = TypeAct.builder()
                .typeAct(typeAct.getTypeAct())
                .lib(typeAct.getLib())
                .build();

        ResponseEntity<List<Integer>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Integer>>() {
                });
        List<Integer> userIdList = response.getBody();
        NotifRequest notifRequest = new NotifRequest();
        notifRequest.setType("NEW_TYPEACT");
        notifRequest.setDate(date);
        notifRequest.setMessage("nouveau type des actes médicaux : " + typeAct.getLib() + " est ajouté");
        for (Integer userId : userIdList) {
            notifRequest.setUser(userId);
            notifSender.sendNotif(notifRequest);
        }

        HistoryRequest historyRequest = new HistoryRequest();
        historyRequest.setMessage("Création du type des actes médicaux " + typeAct.getLib() + "à"+ date );
        historyRequest.setType("TYPEACT");
        historyRequest.setDate(date);
        historySender.sendHistory(historyRequest);

        return typeActRepository.save(newTypeAct);

    }

    public List<TypeAct> getAll() {
        return typeActRepository.findAll();
    }

    public TypeAct getById(int id) {
        return typeActRepository.findById(id).orElseThrow();
    }

    public void delete(int id) {
        typeActRepository.deleteById(id);
        HistoryRequest historyRequest = new HistoryRequest();
        historyRequest.setMessage("Suppression du type des actes médicaux " + typeActRepository.findById(id).get().getLib() + "à"+ date);
        historyRequest.setType("TYPEACT");
        historyRequest.setDate(date);
        historySender.sendHistory(historyRequest);
    }

    public TypeAct updateTypeAct(int id, TypeAct typeAct) {
        TypeAct typeActToUpdate = typeActRepository.findById(id).orElseThrow();
        typeActToUpdate.setTypeAct(typeAct.getTypeAct());
        typeActToUpdate.setLib(typeAct.getLib());
        typeActRepository.save(typeActToUpdate);
        ResponseEntity<List<Integer>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Integer>>() {
                });
        List<Integer> userIdList = response.getBody();
        NotifRequest notifRequest = new NotifRequest();
        notifRequest.setType("UPDATE_TYPEACT");
        notifRequest.setDate(date);
        notifRequest.setMessage("le type des actes médicaux : " + typeAct.getLib() + " est modifié");
        for (Integer userId : userIdList) {
            notifRequest.setUser(userId);
            notifSender.sendNotif(notifRequest);
        }
        HistoryRequest historyRequest = new HistoryRequest();
        historyRequest.setMessage("Modification du type des actes médicaux " + typeAct.getLib() + "à"+ date );
        historyRequest.setType("TYPEACT");
        historyRequest.setDate(date);
        historySender.sendHistory(historyRequest);

        return typeActRepository.save(typeActToUpdate);
    }
}
