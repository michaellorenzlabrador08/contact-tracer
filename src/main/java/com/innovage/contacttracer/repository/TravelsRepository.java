package com.innovage.contacttracer.repository;

import com.innovage.contacttracer.domain.Place;
import com.innovage.contacttracer.domain.Travels;
import com.innovage.contacttracer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelsRepository extends JpaRepository<Travels, Long> {
    List<Travels> findByPlaceAndUser(Place place, User user);
}
