package com.example.spring_boot_jwt.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsernameAndDeletedAtIsNull(String username);

    @Query("""
            select u from User as u where u.username=?1 and u.password=?2
            """)
    Optional<User> findByUsernameAndPasswordAndDeletedAtIsNull(String username, String password);
}
