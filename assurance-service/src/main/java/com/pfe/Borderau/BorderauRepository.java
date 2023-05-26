package com.pfe.Borderau;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorderauRepository extends JpaRepository<Borderau, Long> {
    

    List<Borderau> findBySocieteId(int societeId);
}
