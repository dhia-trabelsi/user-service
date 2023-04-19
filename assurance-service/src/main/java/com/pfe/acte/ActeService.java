package com.pfe.acte;


import org.springframework.stereotype.Service;


@Service
public class ActeService {

	
	private final ActeRepository acteRepository;

	public ActeService(ActeRepository acteRepository) {
		this.acteRepository = acteRepository;
	}
}
