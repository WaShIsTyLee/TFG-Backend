package com.example.flypark1.Model;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class DatabaseConnectionTest {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @PostConstruct
    public void testConnection() {
        System.out.println("Intentando conectar a DB con URL: " + jdbcUrl);
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("¡Conexión exitosa a la base de datos!");
        } catch (Exception e) {
            System.err.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
    }
}