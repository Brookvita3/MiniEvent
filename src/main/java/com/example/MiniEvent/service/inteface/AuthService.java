package com.example.MiniEvent.service.inteface;

import com.google.firebase.auth.UserRecord;

public interface AuthService {
    UserRecord createUser(String email, String password);
}
