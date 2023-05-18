package com.notification.History;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryService {
    

    private final HistoryRepository historyRepository;


    public History save(History history) {
        return historyRepository.save(history);
    }

    public List<History> findAll() {
        return historyRepository.findAll();
    }

    public List<History> findBytype(HistoryType type) {
        return historyRepository.findAllBytype(type);
    }
}
