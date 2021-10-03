package com.innovage.contacttracer.service;

import com.innovage.contacttracer.domain.User;
import com.innovage.contacttracer.dto.UserRegistrationDTO;
import com.innovage.contacttracer.exception.CTRuntimeException;
import com.innovage.contacttracer.repository.UserRepository;
import com.innovage.contacttracer.utils.ResponseModel;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    private final UserRepository userRepo;

    public UserRegistrationService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User registerUser(UserRegistrationDTO dto) {
        User newUser = new User();
        newUser.setFirstName(dto.getFirstName());
        newUser.setLastName(dto.getLastName());
        newUser.setEmail(dto.getEmail());
        return userRepo.save(newUser);

    }
}
