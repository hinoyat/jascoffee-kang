package com.jascoffee.jascoffee.service.user;

import com.jascoffee.jascoffee.dto.user.CustomUserDetails;
import com.jascoffee.jascoffee.entity.user.UserEntity;
import com.jascoffee.jascoffee.repository.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findByAccount(username)
                .orElseThrow(()-> new UsernameNotFoundException("입력하신 아이디로 가입된 사용자를 찾을 수 없습니다. : " +username));
        return new CustomUserDetails(userData);
    }
}