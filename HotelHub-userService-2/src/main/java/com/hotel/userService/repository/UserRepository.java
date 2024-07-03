package com.hotel.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.userService.entities.User1;

public interface UserRepository  extends JpaRepository<User1,String >{
 
}
