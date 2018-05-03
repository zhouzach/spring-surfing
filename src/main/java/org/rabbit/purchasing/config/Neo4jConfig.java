package org.rabbit.purchasing.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;

import java.io.File;

@Configuration
@EnableNeo4jRepositories(basePackages = "org.rabbit.purchasing.repo")
public class Neo4jConfig extends Neo4jConfiguration {

    public Neo4jConfig() {


    }

    public SessionFactory getSessionFactory() {
        return new SessionFactory();
    }

    @Bean(destroyMethod = "shutdown")
    public GraphDatabaseService graphDatabaseService() {
        return new SpringRestGraphDatabase("http://graphdbserver:7474/db/data");
//        return new GraphDatabaseFactory()
//                .newEmbeddedDatabase(new File("/tmp/graphdb"));
    }
}
