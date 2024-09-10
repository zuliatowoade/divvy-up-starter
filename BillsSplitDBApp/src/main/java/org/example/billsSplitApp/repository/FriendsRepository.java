package org.example.billsSplitApp.repository;

import org.example.billsSplitApp.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendsRepository extends JpaRepository<Friends, Long> {
    List<Friends> findByInitiatorName(String InitiatorName);
}
