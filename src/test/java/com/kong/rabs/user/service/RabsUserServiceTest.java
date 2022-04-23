package com.kong.rabs.user.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kong.rabs.exception.RabsException;
import com.kong.rabs.user.entity.RabsUser;
import com.kong.rabs.user.model.UserParam;
import com.kong.rabs.user.repo.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RabsUserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @Test
    void addUser() {
        UserParam userParam = new UserParam();

        when(userRepository.findByAccount(userParam.getAccount())).thenReturn(Optional.empty());

        userService.addUser(userParam);

        verify(userRepository).findByAccount(userParam.getAccount());
        verify(userRepository).save(any(RabsUser.class));
    }

    @Test
    void addUser_AlreadyExists() {
        UserParam userParam = new UserParam();

        RabsUser rabsUser = new RabsUser();

        when(userRepository.findByAccount(userParam.getAccount())).thenReturn(Optional.of(rabsUser));

        assertThrows(RabsException.class, () -> userService.addUser(userParam));

        verify(userRepository).findByAccount(userParam.getAccount());
        verify(userRepository, times(0)).save(any(RabsUser.class));
    }
}