package application.server.Service;

import application.server.persistence.Repo.UserRepo;
import application.server.persistence.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;
   // private final PasswordEncoder passwordEncoder;

    @Autowired
    public MyUserDetailService(UserRepo userRepo)//, PasswordEncoder passwordEncoder)
     {
        this.userRepo = userRepo;
       // this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            email = email.trim().toLowerCase();

            Optional<UserInfo> user = userRepo.findByUserEmail(email);
            System.out.println("email: " + email);

            if (user.isEmpty()) {
                throw new UsernameNotFoundException("No user found with email: " + email);
            }

            return new org.springframework.security.core.userdetails.User(
                    user.get().getUserEmail(),
                    user.get().getUser_password(),
                    user.get().isEnabled(),
                    true,
                    true,
                    true,
                    getAuthorities(user.get().getRole().getRoleName())
            );

        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found: " + email, e);
        }
    }


    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}