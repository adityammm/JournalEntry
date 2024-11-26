package com.myFirstAPI.testAPI.repository;

import com.myFirstAPI.testAPI.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<User> getAllUsersForSA(){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").ne("").ne(null));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        return mongoTemplate.find(query, User.class);
    }
}