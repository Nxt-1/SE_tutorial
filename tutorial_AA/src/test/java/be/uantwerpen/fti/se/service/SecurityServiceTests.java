package be.uantwerpen.fti.se.service;

import be.uantwerpen.fti.se.tutorial.model.Privilege;
import be.uantwerpen.fti.se.tutorial.model.Role;
import be.uantwerpen.fti.se.tutorial.model.User;
import be.uantwerpen.fti.se.tutorial.repository.UserRepository;
import be.uantwerpen.fti.se.tutorial.service.SecurityService;
import be.uantwerpen.fti.se.tutorial.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class SecurityServiceTests {
    @InjectMocks
    private SecurityService securityService;
    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    List<User> userList;
    @Before
    public void init() {
        Privilege p1 = new Privilege("logon");
        Privilege p2 = new Privilege("secret-message");
        Role administrator = new Role("Administrator");
        Role tester = new Role("Tester");
        List<Privilege> privileges = new ArrayList<Privilege>();
        privileges.add(p1);
        tester.setPrivileges(privileges);
        privileges = new ArrayList<>();
        privileges.add(p1);
        privileges.add(p2);
        administrator.setPrivileges(privileges);
        User u1 = new User("U","1");
        u1.setRoles(Arrays.asList(administrator));
        User u2 = new User("U","2");
        u2.setRoles(Arrays.asList(tester));
        userList = new ArrayList<>();
        userList.add(u1);
        userList.add(u2);
//        userRepository.save(userList);
        userRepository.saveAll(userList);
        MockitoAnnotations.initMocks(this);
    }
    @Test(expected = UsernameNotFoundException.class)
    public void nonExistingUsernameTest() {
        when(userService.findByUserName("bla")).thenReturn(null);
        securityService.loadUserByUsername("bla");
    }
}

