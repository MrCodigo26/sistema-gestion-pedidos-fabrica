<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fabrica.pedidos.modelo.Pedido" %>
<%@ page import="com.fabrica.pedidos.modelo.Cliente" %>
<%@ page import="com.fabrica.pedidos.modelo.Producto" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Pedido</title>
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
            Pedido pedido = (Pedido) request.getAttribute("pedido");
            List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
            List<Producto> listaProductos = (List<Producto>) request.getAttribute("listaProductos");
            
            boolean esEdicion = (pedido != null);
            String tituloFormulario = esEdicion ? "Editar Pedido" : "Nuevo Pedido";
            String action = esEdicion ? "actualizar" : "insertar";
            String id = esEdicion ? String.valueOf(pedido.getId()) : "";
            int clienteId = esEdicion ? pedido.getClienteId() : 0;
            int productoId = esEdicion ? pedido.getProductoId() : 0;
            int cantidad = esEdicion ? pedido.getCantidad() : 1;
            String estadoActual = esEdicion ? pedido.getEstado().name() : "PENDIENTE";
        %>
        
        <h1><%= tituloFormulario %></h1>
        
        <div class="formulario">
            <form action="${pageContext.request.contextPath}/pedidos?action=<%= action %>" method="post">
                <% if (esEdicion) { %>
                    <input type="hidden" name="id" value="<%= id %>">
                <% } %>
                
                <% if (!esEdicion) { %>
                <div class="form-grupo">
                    <label for="clienteId">Cliente:</label>
                    <select id="clienteId" name="clienteId" required>
                        <option value="">Seleccione un cliente</option>
                        <%
                            if (listaClientes != null) {
                                for (Cliente cliente : listaClientes) {
                        %>
                        <option value="<%= cliente.getId() %>"><%= cliente.getNombre() %> <%= cliente.getApellido() %></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
                
                <div class="form-grupo">
                    <label for="productoId">Producto:</label>
                    <select id="productoId" name="productoId" required>
                        <option value="">Seleccione un producto</option>
                        <%
                            if (listaProductos != null) {
                                for (Producto producto : listaProductos) {
                        %>
                        <option value="<%= producto.getId() %>"><%= producto.getNombre() %> - $<%= producto.getPrecio() %></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
                <% } %>
                
                <div class="form-grupo">
                    <label for="cantidad">Cantidad:</label>
                    <input type="number" id="cantidad" name="cantidad" value="<%= cantidad %>" min="1" required>
                </div>
                
                <% if (esEdicion) { %>
                <div class="form-grupo">
                    <label for="estado">Estado:</label>
                    <select id="estado" name="estado" required>
                        <option value="PENDIENTE" <%= "PENDIENTE".equals(estadoActual) ? "selected" : "" %>>Pendiente</option>
                        <option value="EN_PROCESO" <%= "EN_PROCESO".equals(estadoActual) ? "selected" : "" %>>En Proceso</option>
                        <option value="DESPACHADO" <%= "DESPACHADO".equals(estadoActual) ? "selected" : "" %>>Despachado</option>
                        <option value="ENTREGADO" <%= "ENTREGADO".equals(estadoActual) ? "selected" : "" %>>Entregado</option>
                    </select>
                </div>
                <% } %>
                
                <button type="submit" class="btn btn-verde">Guardar</button>
                <a href="${pageContext.request.contextPath}/pedidos" class="btn btn-cancelar">Cancelar</a>
            </form>
        </div>
    </div>
    
    <div class="pie-pagina">
        Sistema de Gestión de Pedidos para Fábrica 2026 ©
    </div>
</body>
</html>