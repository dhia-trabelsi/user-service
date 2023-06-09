package com.pfe.Bds;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pfe.Borderau.Borderau;
import com.pfe.Borderau.BorderauRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class bulltinService {


    private final BulltinRepository bulltinRepository;
    private final BorderauRepository borderauRepository;

    public Bulltin saveBulltin(Bulltin bulltin, long code) {
       Borderau b = borderauRepository.findById(code).orElseThrow(() -> new RuntimeException("Borderau not found"));
        bulltin.setValider("en cours");
        bulltin.setBorderau(b);
        return bulltinRepository.save(bulltin);
    }

    public Bulltin getById(int id) {
        return bulltinRepository.findById(id).orElseThrow(() -> new RuntimeException("Bulltin not found"));
    }

    public List<Bulltin> getAll() {
        return bulltinRepository.findAll();
    }

    public void delete(int id) {
        bulltinRepository.deleteById(id);
    }


    public Bulltin updateBulltin(Bulltin updatedBulltin, int id) {
        Bulltin existingBulltin = bulltinRepository.findById(id).orElseThrow(() -> new RuntimeException("Bulltin not found"));
        existingBulltin.setUserID(updatedBulltin.getUserID());
        existingBulltin.setAssur(updatedBulltin.getAssur());
        existingBulltin.setPrestataire(updatedBulltin.getPrestataire());
        existingBulltin.setDateSoin(updatedBulltin.getDateSoin());
        existingBulltin.setMois(updatedBulltin.getMois());
        existingBulltin.setAns(updatedBulltin.getAns());
        existingBulltin.setDateSaisie(updatedBulltin.getDateSaisie());
        existingBulltin.setMtt(updatedBulltin.getMtt());
        existingBulltin.setMttRemb(updatedBulltin.getMttRemb());
        existingBulltin.setFilepath(updatedBulltin.getFilepath());
        return bulltinRepository.save(existingBulltin);
    }

    private final String FOLDER_PATH = "C:/Users/trabe/Desktop/MyFIles/";
    
    
    
    public String uploadImageToFileSystem(MultipartFile file, int id) throws IOException {

        Bulltin bulltin = bulltinRepository.findById(id).orElseThrow();
        String filePath = FOLDER_PATH + file.getOriginalFilename();

        bulltin.setFilepath(filePath);
        bulltinRepository.save(bulltin);

        file.transferTo(new File(filePath));

        return "image uploaded successfully...";
    }

    public Bulltin valider(int id) {
        Bulltin bulltin = bulltinRepository.findById(id).orElseThrow();
        bulltin.setValider("true");
        return bulltinRepository.save(bulltin);
    }
    
    public Bulltin refuser(int id) {
        Bulltin bulltin = bulltinRepository.findById(id).orElseThrow();
        bulltin.setValider("false");
        return bulltinRepository.save(bulltin);
    }
    

    

 
}
