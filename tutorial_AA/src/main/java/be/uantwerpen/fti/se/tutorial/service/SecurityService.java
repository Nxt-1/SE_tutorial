package be.uantwerpen.fti.se.tutorial.service;

import be.uantwerpen.fti.se.tutorial.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SecurityService implements UserDetailsService {
    @Autowired
    UserService userService;
    @Autowired
    private PrivilegeService privilegeService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDetails ud = null;
        User user = userService.findByUserName(userName);
        if (user != null) {
            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.addAll(StreamSupport.stream(privilegeService.findAllForUser(user).spliterator(), false)
                    .map(priv -> new SimpleGrantedAuthority(priv.getName())).collect(Collectors.toList()));
            ud = new org.springframework.security.core.userdetails.User(userName,
                    user.getPassword(), true, true, true, true, authorities);
        } else throw new UsernameNotFoundException("No user with username '" + userName + "' found!");
        return ud;
    }
}

