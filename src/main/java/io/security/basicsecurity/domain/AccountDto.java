package io.security.basicsecurity.domain;

import lombok.Data;

@Data
public class AccountDto {
    private String userName;
    private String password;
    private String email;
    private String age;
    private String role;
}
