package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.model.Address;
import com.example.demo.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO implements  Serializable{
    private String id;
    private String name;
    private int age;
    private Address addresss;

    public UserDTO(User user) {
       this.id = user.getId().toString();
       this.name = user.getName();
       this.age = user.getAge();
      this.addresss = user.getAddresss();

    }
    public UserDTO(String name, int age){
        this.name = name;
        this.age = age;
    }
    }
