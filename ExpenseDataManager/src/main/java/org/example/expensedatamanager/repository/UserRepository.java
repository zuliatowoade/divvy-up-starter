package org.example.expensedatamanager.repository;

import org.example.expensedatamanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
