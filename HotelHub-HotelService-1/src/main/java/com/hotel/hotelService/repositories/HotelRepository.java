package com.hotel.hotelService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.hotelService.entites.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
