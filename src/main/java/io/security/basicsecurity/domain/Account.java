package io.security.basicsecurity.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue
    private Long Id;
    private String userName;
    private String password;
    private String email;
    private String age;
    private String role;
}
