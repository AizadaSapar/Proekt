package com.example.springbootsesson.peaksoft.repasitory;

import com.example.springbootsesson.peaksoft.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User>findByEmail(String email);
}
