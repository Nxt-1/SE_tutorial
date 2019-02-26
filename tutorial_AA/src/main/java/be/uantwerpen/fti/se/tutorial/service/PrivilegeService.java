package be.uantwerpen.fti.se.tutorial.service;

import be.uantwerpen.fti.se.tutorial.model.Privilege;
import be.uantwerpen.fti.se.tutorial.model.User;
import be.uantwerpen.fti.se.tutorial.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService {
    @Autowired
    private PrivilegeRepository privilegeRepository;

    public Iterable<Privilege> findAll() {
        return this.privilegeRepository.findAll();
    }

    public void save(final Privilege privilege) {
        this.privilegeRepository.save(privilege);
    }

    public Iterable<Privilege> findAllForUser(User user){
        return this.privilegeRepository.findAllForUser(user);
    }
}