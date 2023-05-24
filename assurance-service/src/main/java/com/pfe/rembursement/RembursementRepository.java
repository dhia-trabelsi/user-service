package com.pfe.rembursement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RembursementRepository extends JpaRepository<rembursement, Integer> {
    
    void deleteAllByBulltinId(Integer bulltinId);
    List<rembursement> findAllByBulltinId(Integer bulltinId);
}
