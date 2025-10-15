package com.example.demo.graph.repo;

import com.example.demo.graph.model.City;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends Neo4jRepository<City, String> {

    Optional<City> findByName(String name);

    @Query("MATCH (c:City) RETURN c ORDER BY c.name")
    List<City> findAllCities();
}
