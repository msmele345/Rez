package com.mitchmele.restaurantrez.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RezUserRepository extends MongoRepository<RezUser, String> {

    Optional<RezUser> findByUsername(String username);
}
