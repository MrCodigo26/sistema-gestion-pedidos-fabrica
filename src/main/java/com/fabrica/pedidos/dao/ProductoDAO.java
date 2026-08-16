package com.fabrica.pedidos.dao;

import com.fabrica.pedidos.modelo.Producto;
import com.fabrica.pedidos.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de acceso a datos para la entidad Producto.
 * Implementa el CRUD completo: Crear, Leer, Actualizar, Eliminar.
 */
public class ProductoDAO {
    
    // Consultas SQL
    private static final String SQL_INSERTAR = 
            "INSERT INTO productos (nombre, descripcion, precio, stock, categoria) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_SELECCIONAR_POR_ID = 
            "SELECT * FROM productos WHERE id = ?";
    private static final String SQL_SELECCIONAR_TODOS = 
            "SELECT * FROM productos ORDER BY nombre";
    private static final String SQL_ACTUALIZAR = 
            "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, stock = ?, categoria = ? WHERE id = ?";
    private static final String SQL_ELIMINAR = 
            "DELETE FROM productos WHERE id = ?";
    
    /**
     * Inserta un nuevo producto en la base de datos.
     * @param producto El objeto Producto a insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertarProducto(Producto producto) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SQL_INSERTAR)) {
            
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setBigDecimal(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getCategoria());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Obtiene un producto por su ID.
     * @param id El ID del producto a buscar.
     * @return El objeto Producto si se encuentra, o null si no existe.
     */
    public Producto obtenerProductoPorId(int id) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SQL_SELECCIONAR_POR_ID)) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return extraerProductoDeResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener producto por ID: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Obtiene todos los productos de la base de datos.
     * @return Una lista de objetos Producto.
     */
    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(SQL_SELECCIONAR_TODOS)) {
            
            while (rs.next()) {
                productos.add(extraerProductoDeResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los productos: " + e.getMessage());
        }
        return productos;
    }
    
    /**
     * Actualiza un producto existente en la base de datos.
     * @param producto El objeto Producto con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizarProducto(Producto producto) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SQL_ACTUALIZAR)) {
            
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setBigDecimal(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getCategoria());
            ps.setInt(6, producto.getId());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Elimina un producto de la base de datos por su ID.
     * @param id El ID del producto a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminarProducto(int id) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SQL_ELIMINAR)) {
            
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Método privado para mapear un ResultSet a un objeto Producto.
     * @param rs El ResultSet con los datos del producto.
     * @return Un objeto Producto.
     * @throws SQLException si hay un error al leer el ResultSet.
     */
    private Producto extraerProductoDeResultSet(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getInt("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setPrecio(rs.getBigDecimal("precio"));
        producto.setStock(rs.getInt("stock"));
        producto.setCategoria(rs.getString("categoria"));
        return producto;
    }
}