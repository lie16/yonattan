package com.training.yonattan.repository;

import com.training.yonattan.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
  Optional<Users> findByEmail(String email);
}
