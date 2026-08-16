package com.fabrica.pedidos.modelo;

import java.math.BigDecimal;

/**
 * Clase que representa un pedido realizado por un cliente.
 */
public class Pedido {
    
    // Enumeración de estados posibles para un pedido
    public enum Estado {
        PENDIENTE, EN_PROCESO, DESPACHADO, ENTREGADO
    }
    
    private int id;
    private int clienteId;
    private int productoId;
    private int cantidad;
    private String fechaPedido;
    private Estado estado;
    private Integer operadorId;
    private Integer despachadorId;
    
    // Datos adicionales para mostrar en las vistas (JOIN)
    private String nombreCliente;
    private String nombreProducto;
    private BigDecimal precioUnitario;
    private BigDecimal totalPedido;
    
    // Constructor vacío
    public Pedido() {
    }
    
    // Constructor para nuevo pedido
    public Pedido(int clienteId, int productoId, int cantidad) {
        this.clienteId = clienteId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.estado = Estado.PENDIENTE;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getClienteId() {
        return clienteId;
    }
    
    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }
    
    public int getProductoId() {
        return productoId;
    }
    
    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getFechaPedido() {
        return fechaPedido;
    }
    
    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
    
    public Estado getEstado() {
        return estado;
    }
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public Integer getOperadorId() {
        return operadorId;
    }
    
    public void setOperadorId(Integer operadorId) {
        this.operadorId = operadorId;
    }
    
    public Integer getDespachadorId() {
        return despachadorId;
    }
    
    public void setDespachadorId(Integer despachadorId) {
        this.despachadorId = despachadorId;
    }
    
    public String getNombreCliente() {
        return nombreCliente;
    }
    
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    public String getNombreProducto() {
        return nombreProducto;
    }
    
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }
    
    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
    public BigDecimal getTotalPedido() {
        return totalPedido;
    }
    
    public void setTotalPedido(BigDecimal totalPedido) {
        this.totalPedido = totalPedido;
    }
    
    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", productoId=" + productoId +
                ", cantidad=" + cantidad +
                ", estado=" + estado +
                '}';
    }
}