<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fabrica.pedidos.modelo.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Usuarios</title>
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
        <h1>Gestión de Usuarios</h1>
        
        <a href="${pageContext.request.contextPath}/usuarios?action=nuevo" class="btn btn-verde">Agregar Nuevo Usuario</a>
        
        <table class="tabla">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Usuario</th>
                    <th>Rol</th>
                    <th>Email</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
                    if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
                        for (Usuario usuario : listaUsuarios) {
                %>
                <tr>
                    <td><%= usuario.getId() %></td>
                    <td><%= usuario.getNombreUsuario() %></td>
                    <td><%= usuario.getRol() %></td>
                    <td><%= usuario.getEmail() != null ? usuario.getEmail() : "-" %></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/usuarios?action=editar&id=<%= usuario.getId() %>" class="btn btn-editar">Editar</a>
                        <a href="${pageContext.request.contextPath}/usuarios?action=eliminar&id=<%= usuario.getId() %>" class="btn btn-eliminar" 
                           onclick="return confirm('¿Estás seguro de eliminar este usuario?');">Eliminar</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5" style="text-align: center;">No hay usuarios registrados.</td>
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