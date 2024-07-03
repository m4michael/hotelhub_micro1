package com.hotel.rateService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Entity(name="user_rating1")
	public class Rating {

		@Id
		private String ratingId;
		private String userId;
		private String hotelId;
		private int rating;
		private String feedback;
		
		
		
	
	
	
}
