package com.kong.rabs.auth.detail;

import com.kong.rabs.auth.entity.Member;
import com.kong.rabs.auth.repo.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findOneWithAuthoritiesByUsername(username)
            .map(this::createUser)
            .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저가 없습니다."));
    }

    private User createUser(Member member) {
        List<GrantedAuthority> grantedAuthorityList = member.getAuthorities().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
            .collect(Collectors.toList());

        return new User(member.getUsername(), member.getPassword(), grantedAuthorityList);
    }
}
