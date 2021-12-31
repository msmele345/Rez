package com.mitchmele.restaurantrez.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class RezUser {

    private ObjectId _id;

    private String username;
    private String password;
    private boolean active;
    private boolean isAdmin;
    private List<String> roles;
}

