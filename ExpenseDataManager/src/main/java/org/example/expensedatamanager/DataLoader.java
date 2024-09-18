package org.example.expensedatamanager;

//import com.example.databaseservice.model.User;
//import com.example.databaseservice.repository.UserRepository;

import jakarta.annotation.PostConstruct;
//import lombok.var;
import org.example.expensedatamanager.model.Friends;
import org.example.expensedatamanager.model.User;
import org.example.expensedatamanager.repository.FriendsRepository;
import org.example.expensedatamanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class DataLoader {

    private final UserRepository userRepository;
    private final FriendsRepository friendRepository;


    @Autowired
    public DataLoader(UserRepository userRepository, FriendsRepository friendRepository) {
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }

    @PostConstruct
    public void loadData() {
        userRepository.save(new User( null,"akhan", "Ayesha", "Khan", "ayesha.khan@example.com", "123-456-7890"));
        var friend2 = new User(null, "cjernandez", "Carlos", "Hernandez", "carlos.hernandez@example.com", "098-765-4321");
        userRepository.save(friend2);
        var friend1 = new User(null, "mchen", "Mei", "Chen", "mei.chen@example.com", "111-222-3333");
        userRepository.save(friend1);
        userRepository.save(new User(null, "apatel", "Ananya", "Patel", "ananya.patel@example.com", "444-555-6666"));
        userRepository.save(new User(null, "jolawale","Jamal", "Olawale", "jamal.olawale@example.com", "777-888-9999"));
        userRepository.save(new User(null, "sgarcia","Sophia", "Garcia", "sophia.garcia@example.com", "222-333-4444"));
        var friend3 = new User(null, "Lrossi", "Luca", "Rossi", "luca.rossi@example.com", "555-666-7777");
        userRepository.save(friend3);
        userRepository.save(new User(null, "anguyen","Amara", "Nguyen", "amara.nguyen@example.com", "888-999-0000"));
        userRepository.save(new User(null, "rsingh","Ravi", "Singh", "ravi.singh@example.com", "000-111-2222"));
        userRepository.save(new User(null, "ffarsi","Fatima", "Farsi", "fatima.alfarsi@example.com", "333-444-5555"));
        var mainUser = new User(null, "cthomson","Chris", "Thomson", "chris.thomson@example.com", "333-444-5553");
        userRepository.save(mainUser);
        friendRepository.save(new Friends(null, mainUser, friend1, mainUser.getUsername()));
        friendRepository.save(new Friends(null, mainUser, friend2, mainUser.getUsername()));
        friendRepository.save(new Friends(null, mainUser, friend3, mainUser.getUsername()));

    }
}

