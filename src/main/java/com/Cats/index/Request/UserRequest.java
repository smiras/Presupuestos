package com.Cats.index.Request;

import com.Cats.index.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String username;
    private String password;
    private String name;
    private Role role;
    private String empresa;
    private String domicilio;
    private String telefono;

}
