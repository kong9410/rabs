package com.kong.rabs.user.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kong.rabs.user.entity.User;
import com.kong.rabs.user.model.UserParam;
import com.kong.rabs.user.repo.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @Test
    void addUser() {
        UserParam userParam = new UserParam();

        when(userRepository.findByAccount(userParam.getAccount())).thenReturn(Optional.empty());

        boolean actual = userService.addUser(userParam);

        assertTrue(actual);
        verify(userRepository).findByAccount(userParam.getAccount());
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("유저가 이미 있을 때")
    void addUser_AlreadyExists() {
        UserParam userParam = new UserParam();

        User user = new User();

        when(userRepository.findByAccount(userParam.getAccount())).thenReturn(Optional.of(user));

        boolean actual = userService.addUser(userParam);

        assertFalse(actual);
        verify(userRepository).findByAccount(userParam.getAccount());
        verify(userRepository, times(0)).save(any(User.class));
    }
}