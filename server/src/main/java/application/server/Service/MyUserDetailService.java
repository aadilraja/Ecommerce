package application.server.Service;

import application.server.persistence.Repo.UserRepo;
import application.server.persistence.model.UserInfo;
import application.server.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
@Service
public class MyUserDetailService implements UserDetailsService {
    UserRepo userRepo;

    @Autowired
    public MyUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UserNotFoundException {

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        try {
            UserInfo user = userRepo.findByUserEmail(email);
            if (user == null) {
                throw new UserNotFoundException(
                        "No user found with following email ID: " + email);
            }

            return new org.springframework.security.core.userdetails.User(
                    user.getUserEmail(),
                    user.getUser_password().toLowerCase(),
                    user.isEnabled(),
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    getAuthorities(user.getRole()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
