package com.example.demo.graph.model;

import org.springframework.data.neo4j.core.schema.*;

import java.util.HashSet;
import java.util.Set;

@Node("City")
public class City {

    @Id
    private String name;

    private Double lat;
    private Double lon;
    private Integer population;

    // Relaciones salientes con propiedades
    @Relationship(type = "ROUTE", direction = Relationship.Direction.OUTGOING)
    private Set<Route> routes = new HashSet<>();

    public City() {}

    public City(String name, Double lat, Double lon, Integer population) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.population = population;
    }

    // getters y setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getLat() { return lat; }
    public void setLat(Double lat) { this.lat = lat; }
    public Double getLon() { return lon; }
    public void setLon(Double lon) { this.lon = lon; }
    public Integer getPopulation() { return population; }
    public void setPopulation(Integer population) { this.population = population; }
    public Set<Route> getRoutes() { return routes; }
    public void setRoutes(Set<Route> routes) { this.routes = routes; }
}
