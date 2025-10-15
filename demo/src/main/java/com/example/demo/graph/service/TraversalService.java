package com.example.demo.graph.service;

import org.springframework.stereotype.Service;
import org.springframework.data.neo4j.core.Neo4jClient;

import java.util.*;

@Service
public class TraversalService {

    private final Neo4jClient neo4j;

    public TraversalService(Neo4jClient neo4j) {
        this.neo4j = neo4j;
    }

    // Carga un grafo simple en memoria: adjList[from] = [to1, to2, ...]
    private Map<String, List<String>> loadAdjacency() {
        String q = """
            MATCH (a:City)-[:ROUTE]->(b:City)
            RETURN a.name AS from, b.name AS to
            """;

        Map<String, List<String>> adj = new HashMap<>();
        neo4j.query(q).fetch().all().forEach(row -> {
            String from = (String) row.get("from");
            String to = (String) row.get("to");
            adj.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            // Aseguramos presencia de nodos aislados:
            adj.computeIfAbsent(to, k -> new ArrayList<>());
        });
        // ordenar vecinos para respuestas deterministas
        adj.values().forEach(list -> list.sort(Comparator.naturalOrder()));
        return adj;
    }

    public List<String> bfs(String start) {
        Map<String, List<String>> adj = loadAdjacency();
        if (!adj.containsKey(start)) return List.of();

        Set<String> visited = new LinkedHashSet<>();
        Deque<String> queue = new ArrayDeque<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String u = queue.pollFirst();
            for (String v : adj.getOrDefault(u, List.of())) {
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.addLast(v);
                }
            }
        }
        return new ArrayList<>(visited);
    }

    public List<String> dfs(String start) {
        Map<String, List<String>> adj = loadAdjacency();
        if (!adj.containsKey(start)) return List.of();

        Set<String> visited = new LinkedHashSet<>();
        Deque<String> stack = new ArrayDeque<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            String u = stack.pop();
            if (visited.add(u)) {
                List<String> neighbors = adj.getOrDefault(u, List.of());
                // push en orden inverso para visitar en orden alfabético ascendente
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    String v = neighbors.get(i);
                    if (!visited.contains(v)) stack.push(v);
                }
            }
        }
        return new ArrayList<>(visited);
    }
}
