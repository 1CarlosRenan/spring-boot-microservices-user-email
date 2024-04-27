package com.carlosrenan.user.services;

import com.carlosrenan.user.models.UserModel;
import com.carlosrenan.user.producers.UserProducer;
import com.carlosrenan.user.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    final UserRepository userRepository;
    final UserProducer userProducer;


    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        userRepository.save(userModel);
        userProducer.publishMessageEmail(userModel);
        return userModel;
    }
}
