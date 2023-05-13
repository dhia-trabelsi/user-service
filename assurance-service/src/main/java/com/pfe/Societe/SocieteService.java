package com.pfe.Societe;

import java.io.IOException;
import java.util.List;
import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
                .assurance(societe.getAssurance())
                .build();

        System.out.println(newSociete);
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
    }

    public Societe updateSociete(int id, Societe societe) {
        Societe societeToUpdate = societeRepository.findById(id).orElseThrow();
        societeToUpdate = societeToUpdate.builder()
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
        societeRepository.save(societeToUpdate);
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
