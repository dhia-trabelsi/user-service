package com.pfe.TypeAct;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeActService {
    
    private final TypeActRepository typeActRepository;

    public TypeAct save(TypeAct typeAct) {
        TypeAct newTypeAct = TypeAct.builder()
                .lib(typeAct.getLib())
                .build();

        return typeActRepository.save(newTypeAct);

    }

    public List<TypeAct> getAll(){
        return typeActRepository.findAll();
    }

    public TypeAct getById(int id) {
        return typeActRepository.findById(id).orElseThrow();
    }

    public void delete(int id) {
        typeActRepository.deleteById(id);
    }

    public TypeAct updateTypeAct(int id, TypeAct typeAct) {
        TypeAct typeActToUpdate = typeActRepository.findById(id).orElseThrow();
        typeActToUpdate.builder()
                .lib(typeAct.getLib())
                .build();
        return typeActRepository.save(typeActToUpdate);
    }
}
