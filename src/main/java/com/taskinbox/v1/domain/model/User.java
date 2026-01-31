package com.taskinbox.v1.domain.model;

import com.taskinbox.v1.domain.model.enumerations.Role;
import com.taskinbox.v1.infra.dtos.LoginRequest;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
@Builder
@Document(collection = "user")
public class User {

    @Id
    private String id;

    private String mail;

    private String password;

    private Role role;

    public boolean isLoginCorrect(LoginRequest loginRequest, BCryptPasswordEncoder bCryptPasswordEncoder) {
     return bCryptPasswordEncoder.matches(loginRequest.password(), this.password);
    }
}
