package com.pfe.Act;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pfe.TypeAct.TypeAct;
import com.pfe.TypeAct.TypeActRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActService {

    private final ActRepository actRepository;
    private final TypeActRepository typeActRepository;

    public Act save(Act act) {

        TypeAct existingTypeAct = typeActRepository.findById(act.getTypeAct().getTypeAct())
            .orElseThrow(() -> new RuntimeException("TypeAct instance not found in the database"));
        Act newAct = Act.builder()
                .abrv(act.getAbrv())
                .lib(act.getLib())
                .libA(act.getLibA())
                .indice(act.getIndice())
                .mtt(act.getMtt())
                .date(act.getDate())
                .taux(act.getTaux())
                .plafonne(act.getPlafonne())
                .plafond(act.getPlafond())
                .piece(act.getPiece())
                .vign(act.getVign())
                .ctr(act.getCtr())
                .duree(act.getDuree())
                .sexe(act.getSexe())
                .parent(act.getParent())
                .typeAct(existingTypeAct)
                .build();

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
    }

    public Act update(int id, Act act) {

        Act actToUpdate = actRepository.findById(id).orElseThrow();
        actToUpdate.builder()
                .abrv(act.getAbrv())
                .lib(act.getLib())
                .libA(act.getLibA())
                .indice(act.getIndice())
                .mtt(act.getMtt())
                .date(act.getDate())
                .taux(act.getTaux())
                .plafonne(act.getPlafonne())
                .plafond(act.getPlafond())
                .piece(act.getPiece())
                .vign(act.getVign())
                .ctr(act.getCtr())
                .duree(act.getDuree())
                .sexe(act.getSexe())
                .parent(act.getParent())
                .typeAct(act.getTypeAct())
                .build();

        return actRepository.save(actToUpdate);
    }
}
