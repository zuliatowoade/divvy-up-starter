package org.example.expensedatamanager.repository;

import org.example.expensedatamanager.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendsRepository extends JpaRepository<Friends, Long> {
    List<Friends> findByInitiatorName(String InitiatorName);
}
