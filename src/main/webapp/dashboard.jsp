<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.fabrica.pedidos.modelo.Usuario" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Sistema de Gestión</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <%
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            response.sendRedirect("login");
            return;
        }
    %>
    
    <div class="menu-navegacion">
        <div>
            <a href="dashboard">Inicio</a>
            <a href="usuarios">Usuarios</a>
            <a href="clientes">Clientes</a>
            <a href="productos">Productos</a>
            <a href="pedidos">Pedidos</a>
        </div>
        <div class="menu-usuario">
            Bienvenido, <strong><%= usuario.getNombreUsuario() %></strong> 
            | Rol: <%= usuario.getRol() %>
            <a href="logout" class="btn btn-eliminar" style="color: #FFFFFF; text-decoration: none;">Cerrar Sesión</a>
        </div>
    </div>
    
    <div class="contenedor">
        <h1 class="dashboard-titulo">Panel de Control</h1>
        
        <div class="tarjetas-contenedor">
            <div class="tarjeta">
                <h3>Usuarios</h3>
                <p>Gestionar usuarios del sistema</p>
                <a href="usuarios" class="btn btn-primario">Ir a Usuarios</a>
            </div>
            
            <div class="tarjeta">
                <h3>Clientes</h3>
                <p>Administrar clientes</p>
                <a href="clientes" class="btn btn-primario">Ir a Clientes</a>
            </div>
            
            <div class="tarjeta">
                <h3>Productos</h3>
                <p>Gestionar productos de la fábrica</p>
                <a href="productos" class="btn btn-primario">Ir a Productos</a>
            </div>
            
            <div class="tarjeta">
                <h3>Pedidos</h3>
                <p>Ver y gestionar pedidos</p>
                <a href="pedidos" class="btn btn-primario">Ir a Pedidos</a>
            </div>
        </div>
    </div>
    
    <div class="pie-pagina">
        Sistema de Gestión de Pedidos para Fábrica 2026 ©
    </div>
</body>
</html>