package com.edu.rislab9.repo;

import com.edu.rislab9.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepo extends MongoRepository<Person, String> {
}
