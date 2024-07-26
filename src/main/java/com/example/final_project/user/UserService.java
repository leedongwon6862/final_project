package com.example.final_project.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser UserCreate(String username , String password , String email){
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);
        siteUser.setPassword(passwordEncoder.encode(password));
        siteUser.setEmail(email);
         return userRepository.save(siteUser);
    }
}
