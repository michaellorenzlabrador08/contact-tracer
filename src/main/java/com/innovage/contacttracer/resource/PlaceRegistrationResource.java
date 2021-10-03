package com.innovage.contacttracer.resource;

import com.innovage.contacttracer.domain.Place;
import com.innovage.contacttracer.dto.PlaceRegistrationDTO;
import com.innovage.contacttracer.exception.CTRuntimeException;
import com.innovage.contacttracer.service.PlaceRegistrationService;
import com.innovage.contacttracer.service.ResponseService;
import com.innovage.contacttracer.utils.ResponseModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/place")
public class PlaceRegistrationResource {
    private final ResponseService responseService;
    private final PlaceRegistrationService prs;

    public PlaceRegistrationResource(ResponseService responseService, PlaceRegistrationService prs) {
        this.responseService = responseService;
        this.prs = prs;
    }


    @PostMapping(value = "/register")
    private ResponseModel registerPlace(@Valid @RequestBody PlaceRegistrationDTO dto) {
        try {
            Place newPlace = prs.registerPlace(dto);
            return responseService.successResponse(newPlace);
        } catch (CTRuntimeException cte) {
            return responseService.failResponse(cte.getMessage());
        }
    }

    @GetMapping(value = "/hello")
    private ResponseModel hello() {
        return responseService.successResponse("hello");
    }
}
