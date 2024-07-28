package site.dadangsinhhoc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import site.dadangsinhhoc.models.userModel;
import site.dadangsinhhoc.repositories.userRepository;

@Component
public class customUserDetailService implements UserDetailsService {

    @Autowired
    private userRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userModel user = userRepo.findByUserName(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return new customUser(user);
        }
    }
}
