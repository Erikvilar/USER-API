package com.example.demo.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public ResponseEntity<UserDTO> findById(ObjectId id) {
        if (id == null)
            return ResponseEntity.badRequest().build();
        var dbUser = userRepository.findById(id);
        if (dbUser.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new UserDTO(dbUser.get()));
    }

    public ResponseEntity<UserDTO> save(User user) {
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
        dbUserObj.setEmail(user.getEmail());
    
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
