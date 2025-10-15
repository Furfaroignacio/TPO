package com.example.demo;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConnectivityRunner implements CommandLineRunner {
  @Override
  public void run(String... args) throws Exception {
    var uri = "neo4j+s://bb8de3b9.databases.neo4j.io";
    var user = "neo4j";
    var pass = "Pll55gQkuxoARCOFMT3y3TKIjPlG-mGW_Czk6o82M3U";

    try (var driver = GraphDatabase.driver(uri, AuthTokens.basic(user, pass))) {
      driver.verifyConnectivity();
      System.out.println("✅ Conexión a Neo4j establecida correctamente.");
    } catch (Exception e) {
      System.err.println("❌ Error al conectar a Neo4j: " + e.getMessage());
    }
  }
}
