package be.uantwerpen.fti.se.tutorial.repository;

import be.uantwerpen.fti.se.tutorial.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByLastName(String lastName);

    User findByUserName(String userName);
}
