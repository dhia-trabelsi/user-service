package com.pfe.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Integer> {
    List<Child> findAllByUser(User user);
}
