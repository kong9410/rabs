package com.kong.rabs.user.service;

import com.kong.rabs.exception.ErrorType;
import com.kong.rabs.exception.RabsException;
import com.kong.rabs.user.entity.Users;
import com.kong.rabs.user.model.UserParam;
import com.kong.rabs.user.repo.UserRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void saveUser(UserParam userParam) {
        if (userRepository.findByUsername(userParam.getUsername()).isPresent()) {
            throw new RabsException(ErrorType.USER_ALREADY_EXISTS);
        }

        Users users = new Users(userParam.getUsername(), passwordEncoder.encode(userParam.getPassword()));

        userRepository.save(users);
    }
}
