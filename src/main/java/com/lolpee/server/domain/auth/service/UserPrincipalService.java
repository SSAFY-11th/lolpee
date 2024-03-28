package com.lolpee.server.domain.auth.service;

import com.lolpee.server.domain.user.entity.User;
import com.lolpee.server.domain.auth.entity.UserPrincipal;
import com.lolpee.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserPrincipalService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(
            () -> new RuntimeException("존재하지 않은 유저 에러 발생")
        );
        return new UserPrincipal(user);
    }
}
