package com.training.yonattan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.yonattan.entities.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
  Optional<Users> findByEmail(String email);
}
