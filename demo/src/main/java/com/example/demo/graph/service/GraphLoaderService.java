package com.example.demo.graph.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.neo4j.core.Neo4jClient;

@Service
public class GraphLoaderService {

    private final Neo4jClient neo4j;

    public GraphLoaderService(Neo4jClient neo4j) {
        this.neo4j = neo4j;
    }

    @Transactional
    public void seedBasic() {
        String cypher = """
            // Ciudades
            MERGE (:City {name:'CABA',       lat:-34.6037, lon:-58.3816, population:2890000});
            MERGE (:City {name:'La Plata',   lat:-34.9214, lon:-57.9544, population:700000});
            MERGE (:City {name:'Rosario',    lat:-32.9587, lon:-60.6939, population:1200000});
            MERGE (:City {name:'Córdoba',    lat:-31.4201, lon:-64.1888, population:1400000});
            MERGE (:City {name:'Santa Fe',   lat:-31.6333, lon:-60.7000, population:500000});
            MERGE (:City {name:'Paraná',     lat:-31.7444, lon:-60.5175, population:250000});
            MERGE (:City {name:'Mar del Plata', lat:-38.0055, lon:-57.5426, population:620000});
            MERGE (:City {name:'Bahía Blanca',  lat:-38.7196, lon:-62.2724, population:301000});
            MERGE (:City {name:'Mendoza',    lat:-32.8895, lon:-68.8458, population:1150000});
            MERGE (:City {name:'San Luis',   lat:-33.2950, lon:-66.3356, population:200000});
            MERGE (:City {name:'San Juan',   lat:-31.5375, lon:-68.5364, population:470000});
            MERGE (:City {name:'Neuquén',    lat:-38.9516, lon:-68.0591, population:340000});

            // Rutas (bidireccionales)
            MATCH (a:City {name:'CABA'}), (b:City {name:'La Plata'})
            MERGE (a)-[:ROUTE {distanceKm:60,  timeMin:60,  tollARS:500, risk:1}]->(b)
            MERGE (b)-[:ROUTE {distanceKm:60,  timeMin:60,  tollARS:500, risk:1}]->(a);

            MATCH (a:City {name:'CABA'}), (b:City {name:'Rosario'})
            MERGE (a)-[:ROUTE {distanceKm:300, timeMin:210, tollARS:2000, risk:1}]->(b)
            MERGE (b)-[:ROUTE {distanceKm:300, timeMin:210, tollARS:2000, risk:1}]->(a);

            MATCH (a:City {name:'Rosario'}), (b:City {name:'Córdoba'})
            MERGE (a)-[:ROUTE {distanceKm:400, timeMin:300, tollARS:2500, risk:1}]->(b)
            MERGE (b)-[:ROUTE {distanceKm:400, timeMin:300, tollARS:2500, risk:1}]->(a);

            MATCH (a:City {name:'Córdoba'}), (b:City {name:'Mendoza'})
            MERGE (a)-[:ROUTE {distanceKm:650, timeMin:480, tollARS:3000, risk:2}]->(b)
            MERGE (b)-[:ROUTE {distanceKm:650, timeMin:480, tollARS:3000, risk:2}]->(a);

            MATCH (a:City {name:'CABA'}), (b:City {name:'Mar del Plata'})
            MERGE (a)-[:ROUTE {distanceKm:415, timeMin:300, tollARS:2200, risk:1}]->(b)
            MERGE (b)-[:ROUTE {distanceKm:415, timeMin:300, tollARS:2200, risk:1}]->(a);

            MATCH (a:City {name:'Bahía Blanca'}), (b:City {name:'Neuquén'})
            MERGE (a)-[:ROUTE {distanceKm:550, timeMin:420, tollARS:1500, risk:2}]->(b)
            MERGE (b)-[:ROUTE {distanceKm:550, timeMin:420, tollARS:1500, risk:2}]->(a);

            MATCH (a:City {name:'San Luis'}), (b:City {name:'Mendoza'})
            MERGE (a)-[:ROUTE {distanceKm:260, timeMin:210, tollARS:800, risk:1}]->(b)
            MERGE (b)-[:ROUTE {distanceKm:260, timeMin:210, tollARS:800, risk:1}]->(a);

            MATCH (a:City {name:'San Juan'}), (b:City {name:'Mendoza'})
            MERGE (a)-[:ROUTE {distanceKm:170, timeMin:150, tollARS:700, risk:1}]->(b)
            MERGE (b)-[:ROUTE {distanceKm:170, timeMin:150, tollARS:700, risk:1}]->(a);

            MATCH (a:City {name:'Santa Fe'}), (b:City {name:'Paraná'})
            MERGE (a)-[:ROUTE {distanceKm:30,  timeMin:35,  tollARS:300, risk:1}]->(b)
            MERGE (b)-[:ROUTE {distanceKm:30,  timeMin:35,  tollARS:300, risk:1}]->(a);
            """;
        neo4j.query(cypher).run();
    }
}
