package com.myFirstAPI.testAPI.repository;


import com.myFirstAPI.testAPI.entity.ConfigJournalAppEntity;
import com.myFirstAPI.testAPI.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {
}
