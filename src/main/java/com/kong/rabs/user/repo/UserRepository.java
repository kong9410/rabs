package com.kong.rabs.user.repo;

import com.kong.rabs.user.entity.RabsUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<RabsUser, Long> {
    Optional<RabsUser> findByAccount(String account);
}
