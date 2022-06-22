package com.kong.rabs.auth.repo;

import com.kong.rabs.auth.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @EntityGraph(attributePaths = "authorities") // Eager 조회
    Optional<Member> findOneWithAuthoritiesByUsername(String username);
}
