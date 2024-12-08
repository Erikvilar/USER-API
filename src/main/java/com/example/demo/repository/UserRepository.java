package com.example.demo.repository;

import java.util.List;

import org.bson.types.ObjectId;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByCpf(String cpf);
    List<User> searchByName(String name);
    Page<User> findAll(Pageable page);
}
