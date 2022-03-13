package com.kong.rabs.user.service;

import com.kong.rabs.exception.ErrorType;
import com.kong.rabs.exception.RabsException;
import com.kong.rabs.user.entity.User;
import com.kong.rabs.user.model.UserParam;
import com.kong.rabs.user.repo.UserRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public boolean addUser(UserParam userParam) {
        userRepository.findByAccount(userParam.getAccount())
            .orElseThrow(() -> new RabsException(ErrorType.USER_ALREADY_EXISTS));

        User newUser = User.builder()
            .account(userParam.getAccount())
            .password(userParam.getPassword())
            .build();

        userRepository.save(newUser);

        return true;
    }
}
