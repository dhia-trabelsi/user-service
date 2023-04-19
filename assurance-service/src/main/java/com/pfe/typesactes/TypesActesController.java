package com.pfe.typesactes;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/typesactes")
public class TypesActesController {
	
	private final TypesActesService typesActesService;

	public TypesActesController(TypesActesService typesActesService) {
		this.typesActesService = typesActesService;
	}

	@PostMapping
	public ResponseEntity<?> createTypesActes(@Validated @RequestBody Types_actes typesActes) {
		typesActesService.save(typesActes);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<?> getAllTypesActes() {
		return ResponseEntity.ok(typesActesService.getAllTypesActes());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getTypesActesById(@PathVariable Integer id) {
		return ResponseEntity.ok(typesActesService.getTypesActesById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTypesActesById(@PathVariable Integer id) {
		typesActesService.deleteTypesActesById(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody Types_actes typesActes) {
		typesActesService.update(id, typesActes);
		return ResponseEntity.ok().build();
	}


	

}
