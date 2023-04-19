package com.pfe.typesactes;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

@Service
public class TypesActesService {
	
	private final TypesActesRepository typesactesRepository;

	public TypesActesService(TypesActesRepository typesactesRepository) {
		this.typesactesRepository = typesactesRepository;
	}

	public void save(Types_actes typesactes) {

		Types_actes typesactesToSave = Types_actes.builder()
				.Lib_Type_Acte(typesactes.getLib_Type_Acte())
				.build();
		typesactesRepository.save(typesactesToSave);				 
	}

	public List<Types_actes> getAllTypesActes() {

		List<Types_actes> typesactes = new ArrayList<>();
		typesactesRepository.findAll().forEach(typesactes::add);
		return typesactes;
	}

	public Types_actes getTypesActesById(Integer id) {
		return typesactesRepository.findById(id).get();
	}

	public void deleteTypesActesById(Integer id) {
		typesactesRepository.deleteById(id);
	}

	public void update(Integer id, Types_actes typesactes) {
		Types_actes typesactesToUpdate = typesactesRepository.findById(id).get();
		typesactesToUpdate.setLib_Type_Acte(typesactes.getLib_Type_Acte());
		typesactesRepository.save(typesactesToUpdate);

	}


}
