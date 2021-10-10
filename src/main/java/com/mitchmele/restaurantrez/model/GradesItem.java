package com.mitchmele.restaurantrez.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GradesItem {

	private String date;
	private int score;
	private String grade;
}