package com.example.springbootblogrestapi.repositories;

import com.example.springbootblogrestapi.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
