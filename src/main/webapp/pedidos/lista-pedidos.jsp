<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fabrica.pedidos.modelo.Pedido" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Pedidos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <div class="menu-navegacion">
        <div>
            <a href="${pageContext.request.contextPath}/dashboard">Inicio</a>
            <a href="${pageContext.request.contextPath}/usuarios">Usuarios</a>
            <a href="${pageContext.request.contextPath}/clientes">Clientes</a>
            <a href="${pageContext.request.contextPath}/productos">Productos</a>
            <a href="${pageContext.request.contextPath}/pedidos">Pedidos</a>
        </div>
        <div class="menu-usuario">
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-eliminar" style="color: #FFFFFF; text-decoration: none;">Cerrar Sesión</a>
        </div>
    </div>
    
    <div class="contenedor">
        <h1>Gestión de Pedidos</h1>
        
        <a href="${pageContext.request.contextPath}/pedidos?action=nuevo" class="btn btn-verde">Crear Nuevo Pedido</a>
        
        <table class="tabla">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Cliente</th>
                    <th>Producto</th>
                    <th>Cantidad</th>
                    <th>Precio Unitario</th>
                    <th>Total</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Pedido> listaPedidos = (List<Pedido>) request.getAttribute("listaPedidos");
                    if (listaPedidos != null && !listaPedidos.isEmpty()) {
                        for (Pedido pedido : listaPedidos) {
                %>
                <tr>
                    <td><%= pedido.getId() %></td>
                    <td><%= pedido.getNombreCliente() %></td>
                    <td><%= pedido.getNombreProducto() %></td>
                    <td><%= pedido.getCantidad() %></td>
                    <td>$<%= pedido.getPrecioUnitario() %></td>
                    <td>$<%= pedido.getTotalPedido() %></td>
                    <td><%= pedido.getEstado() %></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/pedidos?action=editar&id=<%= pedido.getId() %>" class="btn btn-editar">Editar</a>
                        <a href="${pageContext.request.contextPath}/pedidos?action=eliminar&id=<%= pedido.getId() %>" class="btn btn-eliminar" 
                           onclick="return confirm('¿Estás seguro de eliminar este pedido?');">Eliminar</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="8" style="text-align: center;">No hay pedidos registrados.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
    
    <div class="pie-pagina">
        Sistema de Gestión de Pedidos para Fábrica 2026 © 
    </div>
</body>
</html>