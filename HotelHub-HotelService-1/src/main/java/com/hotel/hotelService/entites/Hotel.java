package com.hotel.hotelService.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="micro_hotels1")
public class Hotel {

	@Id
	private String id;
	
	private String name;
	
	private String location;
	
	private String about;
	
}
