package com.fyordo.shatback.dto.requests.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthEmailRequest {
    @NotBlank
    @Email
    private String login;
    @NotBlank
    @Size(min = 8, max = 30)
    private String password;
}
