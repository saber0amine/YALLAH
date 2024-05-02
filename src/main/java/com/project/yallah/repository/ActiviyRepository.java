package com.project.yallah.repository;

import com.project.yallah.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface ActiviyRepository  extends JpaRepository<Activity, UUID> {
}
