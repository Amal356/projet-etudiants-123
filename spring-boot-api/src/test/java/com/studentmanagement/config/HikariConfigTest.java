package com.studentmanagement.config;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to verify HikariCP connection pool configuration.
 * 
 * **Validates: Requirements 4.1**
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class HikariConfigTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void testHikariDataSourceIsConfigured() {
        // Verify that the DataSource is a HikariDataSource
        assertInstanceOf(HikariDataSource.class, dataSource, 
            "DataSource should be an instance of HikariDataSource");
    }

    @Test
    void testConnectionTimeout() {
        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        
        // Verify connection timeout is set to 30000ms (30 seconds)
        assertEquals(30000, hikariDataSource.getConnectionTimeout(),
            "Connection timeout should be 30000ms");
    }

    @Test
    void testMaximumPoolSize() {
        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        
        // Verify maximum pool size is set to 10
        assertEquals(10, hikariDataSource.getMaximumPoolSize(),
            "Maximum pool size should be 10");
    }

    @Test
    void testMinimumIdle() {
        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        
        // Verify minimum idle connections is set to 5
        assertEquals(5, hikariDataSource.getMinimumIdle(),
            "Minimum idle connections should be 5");
    }

    @Test
    void testIdleTimeout() {
        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        
        // Verify idle timeout is configured (600000ms = 10 minutes)
        assertEquals(600000, hikariDataSource.getIdleTimeout(),
            "Idle timeout should be 600000ms");
    }

    @Test
    void testMaxLifetime() {
        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        
        // Verify max lifetime is configured (1800000ms = 30 minutes)
        assertEquals(1800000, hikariDataSource.getMaxLifetime(),
            "Max lifetime should be 1800000ms");
    }
}
