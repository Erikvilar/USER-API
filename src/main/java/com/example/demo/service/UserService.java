package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<List<UserDTO>> findAll() {
        var dbUser = userRepository.findAll();
        if (dbUser.isEmpty())
            return ResponseEntity.notFound().build();
             
        var userDTOS = dbUser.stream().map(user -> {
            var userDTO = new UserDTO(user);
            return userDTO;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(userDTOS);}

    public ResponseEntity<UserDTO> findById(ObjectId id) {
        if (id == null)
            return ResponseEntity.badRequest().build();
        var dbUser = userRepository.findById(id);
        if (dbUser.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new UserDTO(dbUser.get()));
    }

    public ResponseEntity<UserDTO> save(User user) {
        if (user.getName().isBlank() || user.getAge() <= 0)
            return ResponseEntity.badRequest().build();
        user.setId(ObjectId.get());
        return ResponseEntity.ok(new UserDTO(userRepository.save(user)));
    }

    public ResponseEntity<UserDTO> update(UserDTO user) {
        if (user.getId() == null)
            return ResponseEntity.badRequest().build();
        var objectId = new ObjectId(user.getId());
        var dbUser = userRepository.findById(objectId);
        if (dbUser.isEmpty())
            return ResponseEntity.notFound().build();
        var dbUserObj = dbUser.get();
        dbUserObj.setName(user.getName());
        dbUserObj.setAge(user.getAge());
        return ResponseEntity.ok(new UserDTO(userRepository.save(dbUserObj)));}

    public ResponseEntity<?> delete(ObjectId id) {
        if (id == null)
            return ResponseEntity.badRequest().build();
        userRepository.deleteById(id);
        var dbUser = userRepository.findById(id);
        if (dbUser.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        return ResponseEntity.ok().build();}
}
