package com.fabrica.pedidos.dao;

import com.fabrica.pedidos.modelo.Cliente;
import com.fabrica.pedidos.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de acceso a datos para la entidad Cliente.
 * Implementa el CRUD completo: Crear, Leer, Actualizar, Eliminar.
 */
public class ClienteDAO {
    
    // Consultas SQL
    private static final String SQL_INSERTAR = 
            "INSERT INTO clientes (nombre, apellido, documento, telefono, direccion, email) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECCIONAR_POR_ID = 
            "SELECT * FROM clientes WHERE id = ?";
    private static final String SQL_SELECCIONAR_TODOS = 
            "SELECT * FROM clientes ORDER BY nombre";
    private static final String SQL_ACTUALIZAR = 
            "UPDATE clientes SET nombre = ?, apellido = ?, documento = ?, telefono = ?, direccion = ?, email = ? WHERE id = ?";
    private static final String SQL_ELIMINAR = 
            "DELETE FROM clientes WHERE id = ?";
    
    /**
     * Inserta un nuevo cliente en la base de datos.
     * @param cliente El objeto Cliente a insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertarCliente(Cliente cliente) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SQL_INSERTAR)) {
            
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDocumento());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getEmail());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Obtiene un cliente por su ID.
     * @param id El ID del cliente a buscar.
     * @return El objeto Cliente si se encuentra, o null si no existe.
     */
    public Cliente obtenerClientePorId(int id) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SQL_SELECCIONAR_POR_ID)) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return extraerClienteDeResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente por ID: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Obtiene todos los clientes de la base de datos.
     * @return Una lista de objetos Cliente.
     */
    public List<Cliente> obtenerTodosLosClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(SQL_SELECCIONAR_TODOS)) {
            
            while (rs.next()) {
                clientes.add(extraerClienteDeResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los clientes: " + e.getMessage());
        }
        return clientes;
    }
    
    /**
     * Actualiza un cliente existente en la base de datos.
     * @param cliente El objeto Cliente con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizarCliente(Cliente cliente) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SQL_ACTUALIZAR)) {
            
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDocumento());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getEmail());
            ps.setInt(7, cliente.getId());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Elimina un cliente de la base de datos por su ID.
     * @param id El ID del cliente a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminarCliente(int id) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SQL_ELIMINAR)) {
            
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Método privado para mapear un ResultSet a un objeto Cliente.
     * @param rs El ResultSet con los datos del cliente.
     * @return Un objeto Cliente.
     * @throws SQLException si hay un error al leer el ResultSet.
     */
    private Cliente extraerClienteDeResultSet(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setApellido(rs.getString("apellido"));
        cliente.setDocumento(rs.getString("documento"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setDireccion(rs.getString("direccion"));
        cliente.setEmail(rs.getString("email"));
        cliente.setFechaRegistro(rs.getString("fecha_registro"));
        return cliente;
    }
}