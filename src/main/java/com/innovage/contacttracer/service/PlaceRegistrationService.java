package com.innovage.contacttracer.service;

import com.innovage.contacttracer.domain.Place;
import com.innovage.contacttracer.dto.PlaceRegistrationDTO;
import com.innovage.contacttracer.exception.CTRuntimeException;
import com.innovage.contacttracer.repository.PlaceRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class PlaceRegistrationService {
    private final PlaceRepository placeRepo;

    public PlaceRegistrationService(PlaceRepository placeRepo) {
        this.placeRepo = placeRepo;
    }

    public Place registerPlace(PlaceRegistrationDTO dto) {
        Place newPlace = new Place();
        newPlace.setName(dto.getName());
        newPlace.setAddress(dto.getAddress());
        newPlace.setActive(true);
        newPlace.setImageUrl(null);
        newPlace.setCode(generatePlaceCode());
        return placeRepo.save(newPlace);
    }


    private String generatePlaceCode() {
        String code = RandomString.make(6);
        return code;
    }

}
