package com.kong.rabs.auth.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kong.rabs.exception.RabsException;
import com.kong.rabs.auth.entity.Member;
import com.kong.rabs.auth.dto.SignUpRequest;
import com.kong.rabs.auth.repo.MemberRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @InjectMocks
    AuthService authService;
    @Mock
    MemberRepository memberRepository;

    @Test
    void addUser() {
        SignUpRequest signUpRequest = new SignUpRequest();

        when(memberRepository.findOneWithAuthoritiesByUsername(signUpRequest.getUsername())).thenReturn(Optional.empty());

        authService.signUp(signUpRequest);

        verify(memberRepository).findOneWithAuthoritiesByUsername(signUpRequest.getUsername());
        verify(memberRepository).save(any(Member.class));
    }

    @Test
    void addUser_AlreadyExists() {
        SignUpRequest signUpRequest = new SignUpRequest();

        Member member = new Member();

        when(memberRepository.findOneWithAuthoritiesByUsername(signUpRequest.getUsername())).thenReturn(Optional.of(member));

        assertThrows(RabsException.class, () -> authService.signUp(signUpRequest));

        verify(memberRepository).findOneWithAuthoritiesByUsername(signUpRequest.getUsername());
        verify(memberRepository, times(0)).save(any(Member.class));
    }
}