package com.pfe.Assurance;

import java.io.IOException;
import java.util.List;
import java.io.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssuranceService {

    private final AssuranceRepository assuranceRepository;

    public Assurance save(Assurance assurance) {
        var assuranceToSave = Assurance.builder()
                .Cod_Assur(assurance.getCod_Assur())
                .Lib_Assur(assurance.getLib_Assur())
                .Lib_Assur_A(assurance.getLib_Assur_A())
                .Num_Police(assurance.getNum_Police())
                .Typ_Plafond(assurance.getTyp_Plafond())
                .Dat_Contrat(assurance.getDat_Contrat())
                .Age_Between_Enf(assurance.getAge_Between_Enf())
                .Delai_Cvisite(assurance.getDelai_Cvisite())
                .Duree_Bult_Mut(assurance.getDuree_Bult_Mut())
                .Tel_Assur(assurance.getTel_Assur())
                .Fax_Assur(assurance.getFax_Assur())
                .Prefixe(assurance.getPrefixe())
                .Plaf_Mut(assurance.getPlaf_Mut())
                .Taux_Mut(assurance.getTaux_Mut())
                .mntAdher(assurance.getMntAdher())
                .mntEnf(assurance.getMntEnf())
                .mntConj(assurance.getMntConj())
                .mntPere(assurance.getMntPere())
                .mntMere(assurance.getMntMere())
                .ProratPec(assurance.getProratPec())
                .build();

        return assuranceRepository.save(assuranceToSave);
    }

    public List<Assurance> getAll() {
        return assuranceRepository.findAll();
    }

    public Assurance getById(Long id) {
        return assuranceRepository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        assuranceRepository.deleteById(id);
    }

    public Assurance updateAssurance(Assurance assurance, Long id) {
        Assurance assuranceToUpdate = assuranceRepository.findById(id).orElseThrow();
        assuranceToUpdate.setCod_Assur(assurance.getCod_Assur());
        assuranceToUpdate.setLib_Assur(assurance.getLib_Assur());
        assuranceToUpdate.setLib_Assur_A(assurance.getLib_Assur_A());
        assuranceToUpdate.setNum_Police(assurance.getNum_Police());
        assuranceToUpdate.setTyp_Plafond(assurance.getTyp_Plafond());
        assuranceToUpdate.setDat_Contrat(assurance.getDat_Contrat());
        assuranceToUpdate.setDelai_Cvisite(assurance.getDelai_Cvisite());
        assuranceToUpdate.setAge_Between_Enf(assurance.getAge_Between_Enf());
        assuranceToUpdate.setDuree_Bult_Mut(assurance.getDuree_Bult_Mut());
        assuranceToUpdate.setTel_Assur(assurance.getTel_Assur());
        assuranceToUpdate.setFax_Assur(assurance.getFax_Assur());
        assuranceToUpdate.setPrefixe(assurance.getPrefixe());
        assuranceToUpdate.setPlaf_Mut(assurance.getPlaf_Mut());
        assuranceToUpdate.setTaux_Mut(assurance.getTaux_Mut());
        assuranceToUpdate.setMntAdher(assurance.getMntAdher());
        assuranceToUpdate.setMntEnf(assurance.getMntEnf());
        assuranceToUpdate.setMntConj(assurance.getMntConj());
        assuranceToUpdate.setMntPere(assurance.getMntPere());
        assuranceToUpdate.setMntMere(assurance.getMntMere());
        assuranceToUpdate.setProratPec(assurance.getProratPec());
        assuranceRepository.save(assuranceToUpdate);
        return assuranceToUpdate;
    }

    private final String FOLDER_PATH = "C:/Users/trabe/Desktop/MyFIles/";

    public String uploadImageToFileSystem(MultipartFile file, Long id) throws IOException {

        Assurance assurance = assuranceRepository.findById(id).orElseThrow();
        String filePath = FOLDER_PATH + file.getOriginalFilename();

        assurance.setFilepath(filePath);
        assuranceRepository.save(assurance);

        file.transferTo(new File(filePath));

        return "image uploaded successfully...";
    }

}
