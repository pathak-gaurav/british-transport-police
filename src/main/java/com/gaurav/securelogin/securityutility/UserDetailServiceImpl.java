package com.gaurav.securelogin.securityutility;

import com.gaurav.securelogin.entity.User;
import com.gaurav.securelogin.repository.UserRepository;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElse(null);
        UserBuilder userBuilder = null;

        if (user != null) {
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
            userBuilder.password(user.getPassword());
            String[] stringsOfRoles = user.getAuthorities().stream().map(x -> x.getRoleName()).toArray(String[]::new);
            userBuilder.authorities(stringsOfRoles);
            userBuilder.accountLocked(Boolean.valueOf(user.getEnabled()));
        } else {
            throw new UsernameNotFoundException("Username Not Found:" + username);
        }
        return userBuilder.build();
    }
}
