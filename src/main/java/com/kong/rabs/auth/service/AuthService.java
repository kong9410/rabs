package com.kong.rabs.auth.service;

import com.kong.rabs.auth.dto.Auth;
import com.kong.rabs.auth.jwt.JwtTokenProvider;
import com.kong.rabs.auth.repo.MemberRepository;
import com.kong.rabs.auth.type.AuthorityType;
import com.kong.rabs.exception.ErrorType;
import com.kong.rabs.exception.RabsException;
import com.kong.rabs.auth.dto.SignUpRequest;
import com.kong.rabs.auth.entity.Member;
import com.kong.rabs.auth.entity.Authority;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(SignUpRequest signUpRequest) {
        if (memberRepository.findOneWithAuthoritiesByUsername(signUpRequest.getUsername()).isPresent()) {
            throw new RabsException(ErrorType.USER_ALREADY_EXISTS);
        }

        Authority authority = new Authority(AuthorityType.ROLE_MEMBER.name());
        Member member = new Member(
            signUpRequest.getUsername(),
            passwordEncoder.encode(signUpRequest.getPassword()),
            Set.of(authority)
        );

        memberRepository.save(member);
    }

    public ResponseEntity<Auth.Response> signIn(Auth.Request authDtoRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authDtoRequest.getUsername(), authDtoRequest.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken); // loadUserByUsername 메소드가 실행됨
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);

        return new ResponseEntity<>(new Auth.Response(token), httpHeaders, HttpStatus.OK);
    }
}
