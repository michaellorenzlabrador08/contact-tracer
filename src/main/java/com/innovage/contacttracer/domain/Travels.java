package com.innovage.contacttracer.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "travels")
public class Travels {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @Column(name = "checked_in")
    private LocalDateTime checkedIn;

    @Column(name = "checked_out")
    private LocalDateTime checkOut;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public LocalDateTime getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(LocalDateTime checkedIn) {
        this.checkedIn = checkedIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }
}
