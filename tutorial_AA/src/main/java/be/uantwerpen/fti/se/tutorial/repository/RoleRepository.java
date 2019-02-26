package be.uantwerpen.fti.se.tutorial.repository;

import be.uantwerpen.fti.se.tutorial.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
