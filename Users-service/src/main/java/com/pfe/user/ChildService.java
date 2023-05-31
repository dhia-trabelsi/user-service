package com.pfe.user;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChildService {
    

    private final ChildRepository childRepository;
    private final UserRepository userRepository;

    public Child save(Child child) {
        return childRepository.save(child);
    }

    public Child findById(Integer id) {
        return childRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        childRepository.deleteById(id);
    }

    public Child update(Child child) {
        return childRepository.save(child);
    }


    public List<Child> findByUserId(Integer id) {

        User user = userRepository.findById(id).get();
        return user.getChildren();
    }
}
