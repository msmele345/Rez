package com.mitchmele.restaurantrez.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "restaurant")
public class Restaurant {

	private ObjectId _id;

	private Address address;
	private String restaurantId;
	private String name;
	private String cuisine;
	private String borough;
	private List<GradesItem> grades;
}