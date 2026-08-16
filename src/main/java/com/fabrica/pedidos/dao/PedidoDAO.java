package com.fabrica.pedidos.dao;

import com.fabrica.pedidos.modelo.Pedido;
import com.fabrica.pedidos.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de acceso a datos para la entidad Pedido.
 * Implementa el CRUD completo: Crear, Leer, Actualizar, Eliminar.
 */
public class PedidoDAO {
    
    // Consultas SQL
    private static final String SQL_INSERTAR = 
            "INSERT INTO pedidos (cliente_id, producto_id, cantidad, estado) VALUES (?, ?, ?, 'PENDIENTE')";
    private static final String SQL_SELECCIONAR_POR_ID = 
            "SELECT * FROM pedidos WHERE id = ?";
    private static final String SQL_SELECCIONAR_TODOS = 
            "SELECT p.*, c.nombre AS nombre_cliente, pr.nombre AS nombre_producto, " +
            "pr.precio AS precio_unitario, (p.cantidad * pr.precio) AS total_pedido " +
            "FROM pedidos p " +
            "INNER JOIN clientes c ON p.cliente_id = c.id " +
            "INNER JOIN productos pr ON p.producto_id = pr.id " +
            "ORDER BY p.fecha_pedido DESC";
    private static final String SQL_ACTUALIZAR = 
            "UPDATE pedidos SET cantidad = ?, estado = ? WHERE id = ?";
    private static final String SQL_ACTUALIZAR_ESTADO = 
            "UPDATE pedidos SET estado = ?, operador_id = ?, despachador_id = ? WHERE id = ?";
    private static final String SQL_ELIMINAR = 
            "DELETE FROM pedidos WHERE id = ?";
    
    /**
     * Inserta un nuevo pedido en la base de datos.
     * @param pedido El objeto Pedido a insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertarPedido(Pedido pedido) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SQL_INSERTAR)) {
            
            ps.setInt(1, pedido.getClienteId());
            ps.setInt(2, pedido.getProductoId());
            ps.setInt(3, pedido.getCantidad());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar pedido: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Obtiene un pedido por su ID.
     * @param id El ID del pedido a buscar.
     * @return El objeto Pedido si se encuentra, o null si no existe.
     */
    public Pedido obtenerPedidoPorId(int id) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SQL_SELECCIONAR_POR_ID)) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return extraerPedidoDeResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener pedido por ID: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Obtiene todos los pedidos con información de cliente y producto.
     * @return Una lista de objetos Pedido con datos completos.
     */
    public List<Pedido> obtenerTodosLosPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(SQL_SELECCIONAR_TODOS)) {
            
            while (rs.next()) {
                Pedido pedido = extraerPedidoDeResultSet(rs);
                // Agregar datos adicionales del JOIN
                pedido.setNombreCliente(rs.getString("nombre_cliente"));
                pedido.setNombreProducto(rs.getString("nombre_producto"));
                pedido.setPrecioUnitario(rs.getBigDecimal("precio_unitario"));
                pedido.setTotalPedido(rs.getBigDecimal("total_pedido"));
                pedidos.add(pedido);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los pedidos: " + e.getMessage());
        }
        return pedidos;
    }
    
    /**
     * Actualiza un pedido existente en la base de datos.
     * @param pedido El objeto Pedido con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizarPedido(Pedido pedido) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SQL_ACTUALIZAR)) {
            
            ps.setInt(1, pedido.getCantidad());
            ps.setString(2, pedido.getEstado().name());
            ps.setInt(3, pedido.getId());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar pedido: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Actualiza el estado de un pedido y asigna operador/despachador.
     * @param pedido El objeto Pedido con el nuevo estado.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizarEstadoPedido(Pedido pedido) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SQL_ACTUALIZAR_ESTADO)) {
            
            ps.setString(1, pedido.getEstado().name());
            if (pedido.getOperadorId() != null) {
                ps.setInt(2, pedido.getOperadorId());
            } else {
                ps.setNull(2, Types.INTEGER);
            }
            if (pedido.getDespachadorId() != null) {
                ps.setInt(3, pedido.getDespachadorId());
            } else {
                ps.setNull(3, Types.INTEGER);
            }
            ps.setInt(4, pedido.getId());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar estado del pedido: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Elimina un pedido de la base de datos por su ID.
     * @param id El ID del pedido a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminarPedido(int id) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SQL_ELIMINAR)) {
            
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar pedido: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Método privado para mapear un ResultSet a un objeto Pedido.
     * @param rs El ResultSet con los datos del pedido.
     * @return Un objeto Pedido.
     * @throws SQLException si hay un error al leer el ResultSet.
     */
    private Pedido extraerPedidoDeResultSet(ResultSet rs) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(rs.getInt("id"));
        pedido.setClienteId(rs.getInt("cliente_id"));
        pedido.setProductoId(rs.getInt("producto_id"));
        pedido.setCantidad(rs.getInt("cantidad"));
        pedido.setFechaPedido(rs.getString("fecha_pedido"));
        pedido.setEstado(Pedido.Estado.valueOf(rs.getString("estado")));
        
        // Manejar valores null
        Object operadorId = rs.getObject("operador_id");
        if (operadorId != null) {
            pedido.setOperadorId(rs.getInt("operador_id"));
        }
        
        Object despachadorId = rs.getObject("despachador_id");
        if (despachadorId != null) {
            pedido.setDespachadorId(rs.getInt("despachador_id"));
        }
        
        return pedido;
    }
}