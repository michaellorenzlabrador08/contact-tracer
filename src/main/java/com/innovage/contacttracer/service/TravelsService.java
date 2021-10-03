package com.innovage.contacttracer.service;

import com.innovage.contacttracer.domain.Place;
import com.innovage.contacttracer.domain.Travels;
import com.innovage.contacttracer.domain.User;
import com.innovage.contacttracer.dto.TravelsDTO;
import com.innovage.contacttracer.exception.CTRuntimeException;
import com.innovage.contacttracer.repository.PlaceRepository;
import com.innovage.contacttracer.repository.TravelsRepository;
import com.innovage.contacttracer.repository.UserRepository;
import com.innovage.contacttracer.utils.Constants;
import liquibase.util.CollectionUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TravelsService {

    private final UserRepository userRepo;
    private final PlaceRepository placeRepo;
    private final TravelsRepository travelsRepo;

    public TravelsService(UserRepository userRepo, PlaceRepository placeRepo, TravelsRepository travelsRepo) {
        this.userRepo = userRepo;
        this.placeRepo = placeRepo;
        this.travelsRepo = travelsRepo;
    }

    public Travels checkIn(@Valid @RequestBody TravelsDTO dto) {
        if (Constants.TRAVELS_CHECKIN.equalsIgnoreCase(dto.getType())) {

            Optional<User> user = userRepo.findById(dto.getUserId());
            Optional<Place> place = placeRepo.findByCode(dto.getPlaceCode());

            if (!user.isPresent()) {
                throw new CTRuntimeException(Constants.USER_NOT_FOUND);
            }

            if (!place.isPresent()) {
                throw new CTRuntimeException(Constants.PLACE_CODE_NOT_FOUND);
            }

            Travels newTravels = new Travels();
            newTravels.setUser(user.get());
            newTravels.setPlace(place.get());
            newTravels.setCheckedIn(LocalDateTime.now());
            newTravels.setCheckOut(null);

            return travelsRepo.save(newTravels);

        } else {
            throw new CTRuntimeException(Constants.INVALID_TRAVEL_TYPE);
        }
    }

    public Travels checkOut(@Valid @RequestBody TravelsDTO dto) {
        if (Constants.TRAVELS_CHECKOUT.equalsIgnoreCase(dto.getType())) {

            Optional<User> user = userRepo.findById(dto.getUserId());
            Optional<Place> place = placeRepo.findByCode(dto.getPlaceCode());


            if (!user.isPresent()) {
                throw new CTRuntimeException(Constants.USER_NOT_FOUND);
            }

            if (!place.isPresent()) {
                throw new CTRuntimeException(Constants.PLACE_CODE_NOT_FOUND);
            }

            List<Travels> travelsList = travelsRepo.findByPlaceAndUser(place.get(), user.get());
            List<Travels> todaysTravels = travelsList.stream().filter(t -> null != t.getCheckedIn() && t.getCheckedIn().toLocalDate().equals(LocalDate.now())).collect(Collectors.toList());
            todaysTravels.sort(Comparator.comparing(Travels::getCheckedIn).reversed());

            if (CollectionUtils.isEmpty(todaysTravels)) {
                throw new CTRuntimeException(Constants.CHECKIN_NOT_FOUND);
            }

            todaysTravels.forEach(travels -> {
                travels.setCheckOut(LocalDateTime.now());
            });

            travelsRepo.saveAll(todaysTravels);
            return todaysTravels.get(0);
        } else {
            throw new CTRuntimeException(Constants.INVALID_TRAVEL_TYPE);
        }
    }
}
