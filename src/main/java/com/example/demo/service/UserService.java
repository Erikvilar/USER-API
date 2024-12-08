package com.example.demo.service;


import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return new ResponseEntity<>(new UserDTO(userRepository.save(user)), HttpStatus.CREATED);
    }

    public ResponseEntity<UserDTO> update(UserDTO user) {
        if (user.getId() == null)
            return ResponseEntity.badRequest().build();
        var objectId = new ObjectId(user.getId());
        var dbUser = userRepository.findById(objectId);
        if (dbUser.isEmpty())
            return ResponseEntity.notFound().build();
        var dbUserObj = dbUser.get();
        dbUserObj.setAddress(user.getAddress());
        dbUserObj.setCpf(user.getCpf());
        dbUserObj.setTelefone(user.getTelefone());
        dbUserObj.setName(user.getName());
        dbUserObj.setEmail(user.getEmail());
        return ResponseEntity.ok(new UserDTO(userRepository.save(dbUserObj)));
    }

    public ResponseEntity<?> delete(ObjectId id) {
        if (id == null)
            return ResponseEntity.badRequest().build();
        userRepository.deleteById(id);
        var dbUser = userRepository.findById(id);
        if (dbUser.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        return ResponseEntity.status(204).build();
    }

    public ResponseEntity<UserDTO> findByCpf(String cpf){
        if(cpf == null)
        return ResponseEntity.badRequest().build();
    return ResponseEntity.ok(new UserDTO(userRepository.findByCpf(cpf)));
    }
    public ResponseEntity<List<UserDTO>> searchByName(String name){
        if(name == null)
        return ResponseEntity.badRequest().build();

        List<User> users = userRepository.searchByName(name);
        //   Todo: rever essa implemtenção de mapeamento de user -> userDTO
    return ResponseEntity.ok(
    users.stream()
    .map(user -> new UserDTO(user))
    .collect(Collectors.toList()));
    }

    public Page<UserDTO> getAllPage(Pageable page){
        Page<User> users = userRepository.findAll(page);
        return users.map(UserDTO::convert);
    }
}
