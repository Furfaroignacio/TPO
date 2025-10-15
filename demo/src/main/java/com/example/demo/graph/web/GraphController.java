package com.example.demo.graph.web;

import com.example.demo.graph.repo.CityRepository;
import com.example.demo.graph.service.GraphLoaderService;
import com.example.demo.graph.service.TraversalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/graph")
public class GraphController {

    private final GraphLoaderService loader;
    private final TraversalService traversal;
    private final CityRepository cityRepo;

    public GraphController(GraphLoaderService loader, TraversalService traversal, CityRepository cityRepo) {
        this.loader = loader;
        this.traversal = traversal;
        this.cityRepo = cityRepo;
    }

    @PostMapping("/seed/basic")
    public ResponseEntity<String> seed() {
        loader.seedBasic();
        return ResponseEntity.ok("Seed OK");
    }

    @GetMapping("/cities")
    public ResponseEntity<?> cities() {
        return ResponseEntity.ok(cityRepo.findAllCities());
    }

    @GetMapping("/bfs")
    public ResponseEntity<List<String>> bfs(@RequestParam String start) {
        return ResponseEntity.ok(traversal.bfs(start));
    }

    @GetMapping("/dfs")
    public ResponseEntity<List<String>> dfs(@RequestParam String start) {
        return ResponseEntity.ok(traversal.dfs(start));
    }
}
