package com.java.spring.security.examples.authentication.controller.security.service;

import com.java.spring.security.examples.authentication.controller.security.repository.UserRepository;
import com.java.spring.security.examples.authentication.controller.security.repository.model.User;
import com.java.spring.security.examples.authentication.controller.security.service.model.MyCustomUserDetails;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service(value = "my_custom_user_details_service")
public class MyCustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyCustomUserDetailsService(@Qualifier("user_repository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(username);
            return user.map(value -> new MyCustomUserDetails(
                    value.getUserName(),
                    value.getPassword(),
                    value.getEnabled(),
                    value.getAuthorities()
                            .stream()
                            .map(f ->
                                    new SimpleGrantedAuthority(f.getAuthority()))
                            .collect(Collectors.toList())))
                    .orElseThrow(new Supplier<UsernameNotFoundException>() {
                @Override
                public UsernameNotFoundException get() {
                    return new UsernameNotFoundException("user with user_name:" + username + "; was not found");
                }
            });

    }
}
