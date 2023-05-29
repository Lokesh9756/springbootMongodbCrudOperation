package javaproject.springbootmongocrudoperation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javaproject.springbootmongocrudoperation.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
