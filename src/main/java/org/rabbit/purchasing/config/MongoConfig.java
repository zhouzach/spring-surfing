package org.rabbit.purchasing.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

@Configuration
@EnableMongoRepositories(basePackages = "org.rabbit.purchasing.repo")
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName() {
        return "OrdersDB";
    }

    @Override
    public MongoClient mongoClient() {
        MongoCredential credential = MongoCredential.createMongoCRCredential(
                env.getProperty("mongo.username"),
                "OrdersDB",
                env.getProperty("mongo.password").toCharArray()
        );
        return new MongoClient(
                new ServerAddress("mongodbserver", 37017),
                Arrays.asList(credential));
    }


}
