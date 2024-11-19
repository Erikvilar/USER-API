package com.example.demo.dto;

import java.io.Serializable;

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
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;

    public UserDTO(User user) {
        this.id = user.getId().toString();
        this.name = user.getName();
        this.cpf = user.getCpf();
        this.endereco = user.getEndereco();
        this.email = user.getEmail();
        this.telefone = user.getTelefone();

    }

   
}
