package org.example.billsSplitApp.repository;

import org.example.billsSplitApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
