package simulations;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

/**
 * Gatling stress test simulation for Student API.
 * Simulates 50 concurrent users calling GET /api/etudiants for 30 seconds.
 * 
 * PARTIE 4 - Q2: Tests de stress avec Gatling
 * 
 * To run: mvn gatling:test
 */
public class EtudiantSimulation extends Simulation {
    
    // HTTP Protocol Configuration
    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json")
            .userAgentHeader("Gatling Stress Test");
    
    // Scenario: Get all students
    ScenarioBuilder getAllStudentsScenario = scenario("Liste des étudiants")
            .exec(http("GET /api/etudiants")
                    .get("/api/etudiants")
                    .check(status().is(200))
                    .check(jsonPath("$").exists())
            );
    
    // Scenario: Get student by ID
    ScenarioBuilder getStudentByIdScenario = scenario("Détails d'un étudiant")
            .exec(http("GET /api/etudiants/1")
                    .get("/api/etudiants/1")
                    .check(status().in(200, 404))
            );
    
    // Scenario: Get students by year
    ScenarioBuilder getStudentsByYearScenario = scenario("Étudiants par année")
            .exec(http("GET /api/etudiants with annee param")
                    .get("/api/etudiants")
                    .queryParam("annee", "2023")
                    .check(status().is(200))
            );
    
    // Scenario: Mixed operations
    ScenarioBuilder mixedOperationsScenario = scenario("Opérations mixtes")
            .exec(
                    http("GET all students")
                            .get("/api/etudiants")
                            .check(status().is(200))
            )
            .pause(Duration.ofSeconds(1))
            .exec(
                    http("GET student by ID")
                            .get("/api/etudiants/1")
                            .check(status().in(200, 404))
            )
            .pause(Duration.ofSeconds(1))
            .exec(
                    http("GET students by year with param")
                            .get("/api/etudiants")
                            .queryParam("annee", "2023")
                            .check(status().is(200))
            );
    
    // Setup: Inject users and configure load
    {
        setUp(
                // Scenario 1: Ramp up 50 users over 30 seconds
                getAllStudentsScenario.injectOpen(
                        rampUsers(50).during(Duration.ofSeconds(30))
                ).protocols(httpProtocol),
                
                // Scenario 2: Constant load of 10 users for 20 seconds
                getStudentByIdScenario.injectOpen(
                        constantUsersPerSec(10).during(Duration.ofSeconds(20))
                ).protocols(httpProtocol),
                
                // Scenario 3: Ramp up 20 users over 15 seconds
                getStudentsByYearScenario.injectOpen(
                        rampUsers(20).during(Duration.ofSeconds(15))
                ).protocols(httpProtocol),
                
                // Scenario 4: Mixed operations with 30 users
                mixedOperationsScenario.injectOpen(
                        rampUsers(30).during(Duration.ofSeconds(25))
                ).protocols(httpProtocol)
        ).assertions(
                // Global assertions
                global().responseTime().max().lt(5000), // Max response time < 5s
                global().responseTime().mean().lt(1000), // Mean response time < 1s
                global().successfulRequests().percent().gt(95.0) // Success rate > 95%
        );
    }
}
