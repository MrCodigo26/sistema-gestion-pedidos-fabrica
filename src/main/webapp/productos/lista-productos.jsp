<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fabrica.pedidos.modelo.Producto" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Productos</title>
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
        <h1>Gestión de Productos</h1>
        
        <a href="${pageContext.request.contextPath}/productos?action=nuevo" class="btn btn-verde">Agregar Nuevo Producto</a>
        
        <table class="tabla">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Precio</th>
                    <th>Stock</th>
                    <th>Categoría</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Producto> listaProductos = (List<Producto>) request.getAttribute("listaProductos");
                    if (listaProductos != null && !listaProductos.isEmpty()) {
                        for (Producto producto : listaProductos) {
                %>
                <tr>
                    <td><%= producto.getId() %></td>
                    <td><%= producto.getNombre() %></td>
                    <td><%= producto.getDescripcion() != null ? producto.getDescripcion() : "-" %></td>
                    <td>$<%= producto.getPrecio() %></td>
                    <td><%= producto.getStock() %></td>
                    <td><%= producto.getCategoria() != null ? producto.getCategoria() : "-" %></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/productos?action=editar&id=<%= producto.getId() %>" class="btn btn-editar">Editar</a>
                        <a href="${pageContext.request.contextPath}/productos?action=eliminar&id=<%= producto.getId() %>" class="btn btn-eliminar" 
                           onclick="return confirm('¿Estás seguro de eliminar este producto?');">Eliminar</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="7" style="text-align: center;">No hay productos registrados.</td>
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