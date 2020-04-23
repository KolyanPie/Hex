package ru.edu.kolyanpie.server.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.edu.kolyanpie.server.model.User;
import ru.edu.kolyanpie.server.repos.UserRepo;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("user"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    public boolean addUser(User user) {
        if (existUserByUsername(user.getUsername())) {
            return false;
        }
        String password = user.getPassword();
        if (password.length() < 8) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);
        return true;
    }

    public boolean existUserByUsername(String username) {
        User userFromDb = userRepo.findByUsername(username);

        return userFromDb != null;
    }
}