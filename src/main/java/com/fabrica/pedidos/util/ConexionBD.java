package com.fabrica.pedidos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de utilidad para gestionar la conexión a la base de datos MySQL.
 */
public class ConexionBD {

    // Parámetros de conexión
	private static final String URL = "jdbc:mysql://localhost:3306/fabrica_pedidos?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "admin";

    /**
     * Obtiene una conexión a la base de datos.
     * @return Objeto Connection.
     * @throws SQLException si hay un error al conectar.
     */
    public static Connection obtenerConexion() throws SQLException {
        try {
            // Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver JDBC de MySQL: " + e.getMessage());
            throw new SQLException("Driver JDBC no encontrado.", e);
        }
        // Establecer la conexión
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}