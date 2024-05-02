package com.project.yallah.repository;

import com.project.yallah.model.Activity;
import com.project.yallah.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID>{


    Users findByEmail(String email);


    Users findByName(String name);

}
