package be.uantwerpen.fti.se.tutorial;

import be.uantwerpen.fti.se.tutorial.model.Privilege;
import be.uantwerpen.fti.se.tutorial.model.Role;
import be.uantwerpen.fti.se.tutorial.model.User;
import be.uantwerpen.fti.se.tutorial.repository.PrivilegeRepository;
import be.uantwerpen.fti.se.tutorial.repository.RoleRepository;
import be.uantwerpen.fti.se.tutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Profile("default")
public class DatabaseLoader {
    private final PrivilegeRepository privilegeRepository;
    private final RoleRepository roleRepository; private
    final UserRepository userRepository;
    @Autowired
    public DatabaseLoader(PrivilegeRepository privilegeRepository,
                          RoleRepository roleRepository, UserRepository userRepository) {
        this.privilegeRepository = privilegeRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }
    @PostConstruct
    private void initDatabase() {
        Privilege p1 = new Privilege("logon");
        privilegeRepository.save(p1);
        Privilege p2 = new Privilege("secretmessage"); privilegeRepository.save(p2);
        Role administrator = new Role("Administrator");
        Role tester = new Role("Tester");
        List<Privilege> privileges = new ArrayList<Privilege>();
        privileges.add(p1);
        tester.setPrivileges(privileges);
        roleRepository.save(tester);
        privileges = new ArrayList<Privilege>();
        privileges.add(p1);
        privileges.add(p2);
        administrator.setPrivileges(privileges);
        roleRepository.save(administrator);
        User u1 = new User("Siegfried "," Mercelis ");
        u1.setUserName("u5");
        u1.setPassword("p5");
        List<Role> roles = new ArrayList<>();
        roles.add(administrator);
        u1.setRoles(roles);
        userRepository.save(u1);
        User u2 = new User("Reinout","Eyckerman");
        u2.setUserName("u6");
        u2.setPassword("p6");
        roles = new ArrayList<>();
        roles.add(tester);
        u2.setRoles(roles);
        userRepository.save(u2);
        User u3 = new User("Arne","Aerts");
        u3.setUserName("u7");
        u3.setPassword("p7");
        roles = new ArrayList<>();
        roles.add(tester);
        u3.setRoles(roles);
        userRepository.save(u3);
    }
}

