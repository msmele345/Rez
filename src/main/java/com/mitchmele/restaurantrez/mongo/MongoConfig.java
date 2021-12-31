package com.mitchmele.restaurantrez.mongo;


import com.mitchmele.restaurantrez.restaurant.RestaurantRepository;
import com.mitchmele.restaurantrez.user.RezUserRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = {RezUserRepository.class, RestaurantRepository.class})
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "Practice";
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create();
    }
}
