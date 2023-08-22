package com.training.yonattan.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.training.yonattan.entities.Users;
import com.training.yonattan.repository.UsersRepository;

@Service
public class UsersService implements UserDetailsService {

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return usersRepository.findByEmail(email)
          .orElseThrow(() -> new UsernameNotFoundException("email not found"));
  }

  public UUID getCurrentId(){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        UUID id = null;
        if (principal instanceof Users users) {
            id = users.getUserId();
        } else {
            id = UUID.fromString(principal.toString());
        }
        return id;
  }

  public Users registerUser(Users user) throws Exception {
    boolean userExists = usersRepository.findByEmail(user.getEmail()).isPresent();

    if (!userExists) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        usersRepository.save(user);
        return user;
    } else {
      throw new Exception("email already exist");
    }
  }

}
