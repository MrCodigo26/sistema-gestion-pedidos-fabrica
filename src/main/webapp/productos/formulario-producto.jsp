<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.fabrica.pedidos.modelo.Producto" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Producto</title>
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
        <%
            Producto producto = (Producto) request.getAttribute("producto");
            boolean esEdicion = (producto != null);
            String tituloFormulario = esEdicion ? "Editar Producto" : "Nuevo Producto";
            String action = esEdicion ? "actualizar" : "insertar";
            String id = esEdicion ? String.valueOf(producto.getId()) : "";
            String nombre = esEdicion ? producto.getNombre() : "";
            String descripcion = esEdicion && producto.getDescripcion() != null ? producto.getDescripcion() : "";
            String precio = esEdicion ? String.valueOf(producto.getPrecio()) : "";
            String stock = esEdicion ? String.valueOf(producto.getStock()) : "";
            String categoria = esEdicion && producto.getCategoria() != null ? producto.getCategoria() : "";
        %>
        
        <h1><%= tituloFormulario %></h1>
        
        <div class="formulario">
            <form action="${pageContext.request.contextPath}/productos?action=<%= action %>" method="post">
                <% if (esEdicion) { %>
                    <input type="hidden" name="id" value="<%= id %>">
                <% } %>
                
                <div class="form-grupo">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" value="<%= nombre %>" required>
                </div>
                
                <div class="form-grupo">
                    <label for="descripcion">Descripción:</label>
                    <textarea id="descripcion" name="descripcion" rows="4"><%= descripcion %></textarea>
                </div>
                
                <div class="form-grupo">
                    <label for="precio">Precio:</label>
                    <input type="number" step="0.01" id="precio" name="precio" value="<%= precio %>" required>
                </div>
                
                <div class="form-grupo">
                    <label for="stock">Stock:</label>
                    <input type="number" id="stock" name="stock" value="<%= stock %>" required>
                </div>
                
                <div class="form-grupo">
                    <label for="categoria">Categoría:</label>
                    <input type="text" id="categoria" name="categoria" value="<%= categoria %>">
                </div>
                
                <button type="submit" class="btn btn-verde">Guardar</button>
                <a href="${pageContext.request.contextPath}/productos" class="btn btn-cancelar">Cancelar</a>
            </form>
        </div>
    </div>
    
    <div class="pie-pagina">
        Sistema de Gestión de Pedidos para Fábrica 2026 © 
    </div>
</body>
</html>