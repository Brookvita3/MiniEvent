package com.example.MiniEvent.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {
    private String id;
    private String username;
    private String email;
    private String password;
}
