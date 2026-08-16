<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.fabrica.pedidos.modelo.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Usuario</title>
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
            Usuario usuario = (Usuario) request.getAttribute("usuario");
            boolean esEdicion = (usuario != null);
            String tituloFormulario = esEdicion ? "Editar Usuario" : "Nuevo Usuario";
            String action = esEdicion ? "actualizar" : "insertar";
            String id = esEdicion ? String.valueOf(usuario.getId()) : "";
            String nombreUsuario = esEdicion ? usuario.getNombreUsuario() : "";
            String email = esEdicion && usuario.getEmail() != null ? usuario.getEmail() : "";
            String rolActual = esEdicion ? usuario.getRol().name() : "";
        %>
        
        <h1><%= tituloFormulario %></h1>
        
        <div class="formulario">
            <form action="${pageContext.request.contextPath}/usuarios?action=<%= action %>" method="post">
                <% if (esEdicion) { %>
                    <input type="hidden" name="id" value="<%= id %>">
                <% } %>
                
                <div class="form-grupo">
                    <label for="nombreUsuario">Nombre de Usuario:</label>
                    <input type="text" id="nombreUsuario" name="nombreUsuario" value="<%= nombreUsuario %>" required>
                </div>
                
                <div class="form-grupo">
                    <label for="contrasena">Contraseña:</label>
                    <input type="password" id="contrasena" name="contrasena" value="<%= esEdicion ? usuario.getContrasena() : "" %>" required>
                </div>
                
                <div class="form-grupo">
                    <label for="rol">Rol:</label>
                    <select id="rol" name="rol" required>
                        <option value="">Seleccione un rol</option>
                        <option value="ADMIN" <%= "ADMIN".equals(rolActual) ? "selected" : "" %>>Administrador</option>
                        <option value="OPERADOR" <%= "OPERADOR".equals(rolActual) ? "selected" : "" %>>Operador</option>
                        <option value="DESPACHADOR" <%= "DESPACHADOR".equals(rolActual) ? "selected" : "" %>>Despachador</option>
                        <option value="CLIENTE" <%= "CLIENTE".equals(rolActual) ? "selected" : "" %>>Cliente</option>
                    </select>
                </div>
                
                <div class="form-grupo">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="<%= email %>">
                </div>
                
                <button type="submit" class="btn btn-verde">Guardar</button>
                <a href="${pageContext.request.contextPath}/usuarios" class="btn btn-cancelar">Cancelar</a>
            </form>
        </div>
    </div>
    
    <div class="pie-pagina">
        Sistema de Gestión de Pedidos para Fábrica 2026 © 
    </div>
</body>
</html>