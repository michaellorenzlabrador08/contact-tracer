package com.innovage.contacttracer.resource;

import com.innovage.contacttracer.domain.User;
import com.innovage.contacttracer.dto.UserRegistrationDTO;
import com.innovage.contacttracer.exception.CTRuntimeException;
import com.innovage.contacttracer.service.ResponseService;
import com.innovage.contacttracer.service.UserRegistrationService;
import com.innovage.contacttracer.utils.ResponseModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserRegistrationResource {

    private final ResponseService responseService;
    private final UserRegistrationService urs;

    public UserRegistrationResource(ResponseService responseService, UserRegistrationService urs) {
        this.responseService = responseService;
        this.urs = urs;
    }


    @PostMapping("/register")
    private ResponseModel registerUser(@Valid @RequestBody UserRegistrationDTO dto) {
        try {
            User newUser = urs.registerUser(dto);
            return responseService.successResponse(newUser);
        } catch (CTRuntimeException cte) {
            return responseService.failResponse(cte.getMessage());
        }
    }
}
