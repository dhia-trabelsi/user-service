package com.pfe.Societe;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.pfe.util.Authuser;
import com.pfe.util.HistoryRequest;
import com.pfe.util.HistorySender;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocieteService {

    private final SocieteRepository societeRepository;
    private final HistorySender historySender;
    private final Authuser authuser;

    RestTemplate restTemplate = new RestTemplate();

    LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

    public Societe CreateSociete(Societe societe) {
        Societe newSociete = Societe.builder()
                .Lib(societe.getLib())
                .LibA(societe.getLibA())
                .Adresse(societe.getAdresse())
                .AdresseA(societe.getAdresseA())
                .Tel(societe.getTel())
                .Fax(societe.getFax())
                .CodeTva(societe.getCodeTva())
                .CodeRetr(societe.getCodeRetr())
                .NumRetr(societe.getNumRetr())
                .RepWeb(societe.getRepWeb())
                .Regime(societe.getRegime())
                .NumPolice(societe.getNumPolice())
                .AdrElectronique(societe.getAdrElectronique())
                .CodPay(societe.getCodPay())
                .AgeMin(societe.getAgeMin())
                .AgeMax(societe.getAgeMax())
                .assurance(societe.getAssurance())
                .build();

        HistoryRequest historyRequest = new HistoryRequest();
        historyRequest.setMessage("Création de la société " + societe.getLib() + "à"+ date );
        historyRequest.setType("Societe");
        historyRequest.setUser(authuser.getAuthId());
        historyRequest.setDate(date);
        historySender.sendHistory(historyRequest);

        return societeRepository.save(newSociete);

    }

    public Societe getById(int id) {
        return societeRepository.findById(id).orElseThrow();
    }

    public List<Societe> getAll() {
        return societeRepository.findAll();
    }

    public void delete(int id) {
        
        
        societeRepository.deleteById(id);
        HistoryRequest historyRequest = new HistoryRequest();
        historyRequest.setMessage("Suppression de la société " + societeRepository.findById(id).orElseThrow().getLib() + "à"+ date );
        historyRequest.setType("Societe");
        historyRequest.setUser(authuser.getAuthId());
        historyRequest.setDate(date);
        historySender.sendHistory(historyRequest);

    }

    public Societe updateSociete(int id, Societe societe) {
        Societe societeToUpdate = societeRepository.findById(id).orElseThrow();
        societeToUpdate.setLib(societe.getLib());
        societeToUpdate.setLibA(societe.getLibA());
        societeToUpdate.setAdresse(societe.getAdresse());
        societeToUpdate.setAdresseA(societe.getAdresseA());
        societeToUpdate.setTel(societe.getTel());
        societeToUpdate.setFax(societe.getFax());
        societeToUpdate.setCodeTva(societe.getCodeTva());
        societeToUpdate.setCodeRetr(societe.getCodeRetr());
        societeToUpdate.setNumRetr(societe.getNumRetr());
        societeToUpdate.setRepWeb(societe.getRepWeb());
        societeToUpdate.setRegime(societe.getRegime());
        societeToUpdate.setNumPolice(societe.getNumPolice());
        societeToUpdate.setAdrElectronique(societe.getAdrElectronique());
        societeToUpdate.setCodPay(societe.getCodPay());
        societeToUpdate.setAgeMin(societe.getAgeMin());
        societeToUpdate.setAgeMax(societe.getAgeMax());
        societeToUpdate.setAssurance(societe.getAssurance());
        societeRepository.save(societeToUpdate);

        HistoryRequest historyRequest = new HistoryRequest();
        historyRequest.setMessage("Modification de la société " + societe.getLib() + "à"+ date );
        historyRequest.setType("Societe");
        historyRequest.setUser(authuser.getAuthId());
        historySender.sendHistory(historyRequest);
        historyRequest.setDate(date);
        return societeToUpdate;
    }
    

    public List<Societe> getByAssurance(long id) {
        return societeRepository.findAllByAssurance(id);
    }

    private final String FOLDER_PATH = "C:/Users/trabe/Desktop/MyFIles/";

    public String uploadImageToFileSystem(MultipartFile file, int id) throws IOException {

        Societe societe = societeRepository.findById(id).orElseThrow();
        String filePath = FOLDER_PATH + file.getOriginalFilename();

        societe.setFilepath(filePath);
        societeRepository.save(societe);

        file.transferTo(new File(filePath));

        return "image uploaded successfully...";
    }

}
