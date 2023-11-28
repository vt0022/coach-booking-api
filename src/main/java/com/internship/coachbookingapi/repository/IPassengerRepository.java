package com.internship.coachbookingapi.repository;

import com.internship.coachbookingapi.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IPassengerRepository extends JpaRepository<Passenger, UUID> {
    List<Passenger> findByPhoneStartingWith(String phone);
}
