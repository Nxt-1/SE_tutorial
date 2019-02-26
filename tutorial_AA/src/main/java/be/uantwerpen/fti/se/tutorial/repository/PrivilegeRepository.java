package be.uantwerpen.fti.se.tutorial.repository;

import be.uantwerpen.fti.se.tutorial.model.Privilege;
import be.uantwerpen.fti.se.tutorial.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
    @Query(value = "select p from User u left join u.roles r left join r.privileges p where u =:usr")
    Iterable<Privilege> findAllForUser(@Param("usr") User user);
}
