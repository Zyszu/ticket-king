package pl.matzysz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.matzysz.domain.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("myAppUserDetailsService")
public class MyAppUserDetailsService implements UserDetailsService {

    private UserService UserService;

    @Autowired
    public MyAppUserDetailsService(UserService UserService) {
        this.UserService = UserService;
    }

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        pl.matzysz.domain.User user = UserService.getUserByEmail(email);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }

    // Converts User user to org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(pl.matzysz.domain.User appUser, List<GrantedAuthority> authorities) {

        return new User(appUser.getEmail(), appUser.getPassword(), appUser.isActive(),
                true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> roleList) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        // Build user's authorities
        for (Role role : roleList) {
            setAuths.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return new ArrayList<GrantedAuthority>(setAuths);
    }
}

