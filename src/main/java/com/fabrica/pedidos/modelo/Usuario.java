package com.fabrica.pedidos.modelo;

/**
 * Clase que representa un usuario del sistema con su rol correspondiente.
 */
public class Usuario {
    
    // Enumeración de roles disponibles en el sistema
    public enum Rol {
        ADMIN, DESPACHADOR, OPERADOR, CLIENTE
    }
    
    private int id;
    private String nombreUsuario;
    private String contrasena;
    private Rol rol;
    private String email;
    private String fechaRegistro;
    
    // Constructor vacío
    public Usuario() {
    }
    
    // Constructor para creación de nuevos usuarios
    public Usuario(String nombreUsuario, String contrasena, Rol rol, String email) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.email = email;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getContrasena() {
        return contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public Rol getRol() {
        return rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFechaRegistro() {
        return fechaRegistro;
    }
    
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", rol=" + rol +
                ", email='" + email + '\'' +
                '}';
    }
}