package be.uantwerpen.fti.se.tutorial.service;

import be.uantwerpen.fti.se.tutorial.model.Role;
import be.uantwerpen.fti.se.tutorial.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Iterable<Role> findAll() {
        return this.roleRepository.findAll();
    }

    public void save(final Role role) {
        this.roleRepository.save(role);
    }
}