<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.fabrica.pedidos.modelo.Cliente" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Cliente</title>
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
            Cliente cliente = (Cliente) request.getAttribute("cliente");
            boolean esEdicion = (cliente != null);
            String tituloFormulario = esEdicion ? "Editar Cliente" : "Nuevo Cliente";
            String action = esEdicion ? "actualizar" : "insertar";
            String id = esEdicion ? String.valueOf(cliente.getId()) : "";
            String nombre = esEdicion ? cliente.getNombre() : "";
            String apellido = esEdicion ? cliente.getApellido() : "";
            String documento = esEdicion ? cliente.getDocumento() : "";
            String telefono = esEdicion && cliente.getTelefono() != null ? cliente.getTelefono() : "";
            String direccion = esEdicion && cliente.getDireccion() != null ? cliente.getDireccion() : "";
            String email = esEdicion && cliente.getEmail() != null ? cliente.getEmail() : "";
        %>
        
        <h1><%= tituloFormulario %></h1>
        
        <div class="formulario">
            <form action="${pageContext.request.contextPath}/clientes?action=<%= action %>" method="post">
                <% if (esEdicion) { %>
                    <input type="hidden" name="id" value="<%= id %>">
                <% } %>
                
                <div class="form-grupo">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" value="<%= nombre %>" required>
                </div>
                
                <div class="form-grupo">
                    <label for="apellido">Apellido:</label>
                    <input type="text" id="apellido" name="apellido" value="<%= apellido %>" required>
                </div>
                
                <div class="form-grupo">
                    <label for="documento">Documento:</label>
                    <input type="text" id="documento" name="documento" value="<%= documento %>" required>
                </div>
                
                <div class="form-grupo">
                    <label for="telefono">Teléfono:</label>
                    <input type="text" id="telefono" name="telefono" value="<%= telefono %>">
                </div>
                
                <div class="form-grupo">
                    <label for="direccion">Dirección:</label>
                    <input type="text" id="direccion" name="direccion" value="<%= direccion %>">
                </div>
                
                <div class="form-grupo">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="<%= email %>">
                </div>
                
                <button type="submit" class="btn btn-verde">Guardar</button>
                <a href="${pageContext.request.contextPath}/clientes" class="btn btn-cancelar">Cancelar</a>
            </form>
        </div>
    </div>
    
    <div class="pie-pagina">
        Sistema de Gestión de Pedidos para Fábrica 2026 ©
    </div>
</body>
</html>