package com.example.demo.graph.model;

import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
public class Route {

    @Id @GeneratedValue
    private Long id;

    private Integer distanceKm;
    private Integer timeMin;
    private Integer tollARS;
    private Integer risk;

    @TargetNode
    private City to;

    public Route() {}

    public Route(City to, Integer distanceKm, Integer timeMin, Integer tollARS, Integer risk) {
        this.to = to;
        this.distanceKm = distanceKm;
        this.timeMin = timeMin;
        this.tollARS = tollARS;
        this.risk = risk;
    }

    // getters y setters
    public Long getId() { return id; }
    public Integer getDistanceKm() { return distanceKm; }
    public void setDistanceKm(Integer distanceKm) { this.distanceKm = distanceKm; }
    public Integer getTimeMin() { return timeMin; }
    public void setTimeMin(Integer timeMin) { this.timeMin = timeMin; }
    public Integer getTollARS() { return tollARS; }
    public void setTollARS(Integer tollARS) { this.tollARS = tollARS; }
    public Integer getRisk() { return risk; }
    public void setRisk(Integer risk) { this.risk = risk; }
    public City getTo() { return to; }
    public void setTo(City to) { this.to = to; }
}
