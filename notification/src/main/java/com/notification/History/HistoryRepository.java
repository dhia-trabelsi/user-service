package com.notification.History;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {

    List<History> findAllBytype(HistoryType type);
    
}
