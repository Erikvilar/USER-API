package com.example.demo.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;







@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private  UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") ObjectId id) {
        return userService.findById(id);

    }
   

    @PostMapping("/save")
    public ResponseEntity<UserDTO> create(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO user) {
        return userService.update(user);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") ObjectId id) {
        return userService.delete(id);
    }
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UserDTO> findByCpf(@PathVariable("cpf")  String cpf) {
        return userService.findByCpf(cpf);
    }
    @GetMapping("/nameuser/{name}")
    public ResponseEntity<List<UserDTO>> searchByName(@PathVariable("name")  String name) {
        return userService.searchByName(name);
    }
    @GetMapping("/pageable")
    public Page<UserDTO> pageable(Pageable page) {
        return userService.getAllPage(page);
    }
    
    

}
