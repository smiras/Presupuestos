package com.Cats.index.Response;

import com.Cats.index.Enum.Role;
import com.Cats.index.Enum.Services;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;
    private Boolean active;
    private Role rol;
    private List<Services> services;
}
