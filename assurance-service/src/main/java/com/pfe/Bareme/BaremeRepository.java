package com.pfe.Bareme;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaremeRepository extends JpaRepository<Bareme, Long> {

    Bareme findByAbrv(String abrv);
    
}
