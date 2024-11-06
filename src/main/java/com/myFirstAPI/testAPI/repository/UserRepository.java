package com.myFirstAPI.testAPI.repository;


import com.myFirstAPI.testAPI.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);

    void deleteByUserName(String username);
}
