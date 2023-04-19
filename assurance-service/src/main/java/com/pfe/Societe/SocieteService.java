package com.pfe.Societe;
import java.util.List;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class SocieteService {
    
    private final SocieteRepository societeRepository;           
    
    
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
                    .build();

        System.out.println(newSociete);
        return societeRepository.save(newSociete);
    }

    public Societe getById(int id) {
        return societeRepository.findById(id).orElseThrow();
    }

    public List<Societe> getAll() {
        return  societeRepository.findAll();
    }

    
}