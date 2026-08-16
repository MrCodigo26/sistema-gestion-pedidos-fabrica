<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fabrica.pedidos.modelo.Cliente" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Clientes</title>
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
        <h1>Gestión de Clientes</h1>
        
        <a href="${pageContext.request.contextPath}/clientes?action=nuevo" class="btn btn-verde">Agregar Nuevo Cliente</a>
        
        <table class="tabla">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Documento</th>
                    <th>Teléfono</th>
                    <th>Email</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
                    if (listaClientes != null && !listaClientes.isEmpty()) {
                        for (Cliente cliente : listaClientes) {
                %>
                <tr>
                    <td><%= cliente.getId() %></td>
                    <td><%= cliente.getNombre() %></td>
                    <td><%= cliente.getApellido() %></td>
                    <td><%= cliente.getDocumento() %></td>
                    <td><%= cliente.getTelefono() != null ? cliente.getTelefono() : "-" %></td>
                    <td><%= cliente.getEmail() != null ? cliente.getEmail() : "-" %></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/clientes?action=editar&id=<%= cliente.getId() %>" class="btn btn-editar">Editar</a>
                        <a href="${pageContext.request.contextPath}/clientes?action=eliminar&id=<%= cliente.getId() %>" class="btn btn-eliminar" 
                           onclick="return confirm('¿Estás seguro de eliminar este cliente?');">Eliminar</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="7" style="text-align: center;">No hay clientes registrados.</td>
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