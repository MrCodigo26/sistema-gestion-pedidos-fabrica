package com.fabrica.pedidos.dao;

import com.fabrica.pedidos.modelo.Usuario;
import com.fabrica.pedidos.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de acceso a datos para la entidad Usuario.
 * Implementa el CRUD completo: Crear, Leer, Actualizar, Eliminar.
 */
public class UsuarioDAO {
    
    // Consultas SQL
    private static final String SQL_INSERTAR = 
            "INSERT INTO usuarios (nombre_usuario, contrasena, rol, email) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECCIONAR_POR_ID = 
            "SELECT * FROM usuarios WHERE id = ?";
    private static final String SQL_SELECCIONAR_TODOS = 
            "SELECT * FROM usuarios ORDER BY nombre_usuario";
    private static final String SQL_ACTUALIZAR = 
            "UPDATE usuarios SET nombre_usuario = ?, contrasena = ?, rol = ?, email = ? WHERE id = ?";
    private static final String SQL_ELIMINAR = 
            "DELETE FROM usuarios WHERE id = ?";
    private static final String SQL_VALIDAR_LOGIN = 
            "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";
    
    /**
     * Inserta un nuevo usuario en la base de datos.
     * @param usuario El objeto Usuario a insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertarUsuario(Usuario usuario) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SQL_INSERTAR)) {
            
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasena());
            ps.setString(3, usuario.getRol().name());
            ps.setString(4, usuario.getEmail());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Obtiene un usuario por su ID.
     * @param id El ID del usuario a buscar.
     * @return El objeto Usuario si se encuentra, o null si no existe.
     */
    public Usuario obtenerUsuarioPorId(int id) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SQL_SELECCIONAR_POR_ID)) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return extraerUsuarioDeResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener usuario por ID: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Obtiene todos los usuarios de la base de datos.
     * @return Una lista de objetos Usuario.
     */
    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(SQL_SELECCIONAR_TODOS)) {
            
            while (rs.next()) {
                usuarios.add(extraerUsuarioDeResultSet(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los usuarios: " + e.getMessage());
        }
        return usuarios;
    }
    
    /**
     * Actualiza un usuario existente en la base de datos.
     * @param usuario El objeto Usuario con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizarUsuario(Usuario usuario) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SQL_ACTUALIZAR)) {
            
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasena());
            ps.setString(3, usuario.getRol().name());
            ps.setString(4, usuario.getEmail());
            ps.setInt(5, usuario.getId());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Elimina un usuario de la base de datos por su ID.
     * @param id El ID del usuario a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminarUsuario(int id) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SQL_ELIMINAR)) {
            
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Valida las credenciales de un usuario para el login.
     * @param nombreUsuario El nombre de usuario.
     * @param contrasena La contraseña del usuario.
     * @return El objeto Usuario si las credenciales son correctas, null si no.
     */
    public Usuario validarLogin(String nombreUsuario, String contrasena) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(SQL_VALIDAR_LOGIN)) {
            
            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return extraerUsuarioDeResultSet(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al validar login: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Método privado para mapear un ResultSet a un objeto Usuario.
     * @param rs El ResultSet con los datos del usuario.
     * @return Un objeto Usuario.
     * @throws SQLException si hay un error al leer el ResultSet.
     */
    private Usuario extraerUsuarioDeResultSet(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setNombreUsuario(rs.getString("nombre_usuario"));
        usuario.setContrasena(rs.getString("contrasena"));
        usuario.setRol(Usuario.Rol.valueOf(rs.getString("rol")));
        usuario.setEmail(rs.getString("email"));
        usuario.setFechaRegistro(rs.getString("fecha_registro"));
        return usuario;
    }
}