package com.project.yallah.repository;

import com.project.yallah.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID>{
    Optional<Users> findByName(String name);

    boolean findByEmailAndPassword(String email, String passwrod);
}
