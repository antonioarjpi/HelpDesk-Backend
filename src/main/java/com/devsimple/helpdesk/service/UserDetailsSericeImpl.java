package com.devsimple.helpdesk.service;

import com.devsimple.helpdesk.model.User;
import com.devsimple.helpdesk.repository.UserRepository;
import com.devsimple.helpdesk.security.UserSS;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsSericeImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsSericeImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()){
            return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getPassword(), user.get().getProfiles());
        }
        throw new UsernameNotFoundException(email);
    }
}
