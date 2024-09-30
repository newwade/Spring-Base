package com.projecto.baseapi.repository;

import com.projecto.baseapi.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @EntityGraph(attributePaths = {"contacts"})
    Optional<User> findByEmailAddress(String emailAddress);
}
