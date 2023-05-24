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

    private final String FOLDER_PATH = "C:/Users/trabe/Desktop/MyFIles/";
    
    
    
    public String uploadImageToFileSystem(MultipartFile file, int id) throws IOException {

        Bulltin bulltin = bulltinRepository.findById(id).orElseThrow();
        String filePath = FOLDER_PATH + file.getOriginalFilename();

        bulltin.setFilepath(filePath);
        bulltinRepository.save(bulltin);

        file.transferTo(new File(filePath));

        return "image uploaded successfully...";
    }

    

 
}
