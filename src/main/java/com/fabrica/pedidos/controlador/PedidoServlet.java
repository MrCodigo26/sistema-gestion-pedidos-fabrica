package com.fabrica.pedidos.controlador;

import com.fabrica.pedidos.dao.PedidoDAO;
import com.fabrica.pedidos.dao.ClienteDAO;
import com.fabrica.pedidos.dao.ProductoDAO;
import com.fabrica.pedidos.modelo.Pedido;
import com.fabrica.pedidos.modelo.Cliente;
import com.fabrica.pedidos.modelo.Producto;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet que maneja el CRUD de Pedidos.
 */
@WebServlet("/pedidos")
public class PedidoServlet extends HttpServlet {
    
    private PedidoDAO pedidoDAO;
    private ClienteDAO clienteDAO;
    private ProductoDAO productoDAO;
    
    @Override
    public void init() {
        pedidoDAO = new PedidoDAO();
        clienteDAO = new ClienteDAO();
        productoDAO = new ProductoDAO();
    }
    
    /**
     * Maneja las peticiones GET. Se usa para listar y mostrar formularios.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }
        
        switch (action) {
            case "nuevo":
                mostrarFormularioNuevo(request, response);
                break;
            case "editar":
                mostrarFormularioEditar(request, response);
                break;
            case "eliminar":
                eliminarPedido(request, response);
                break;
            case "listar":
            default:
                listarPedidos(request, response);
                break;
        }
    }
    
    /**
     * Maneja las peticiones POST. Se usa para crear y actualizar.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }
        
        switch (action) {
            case "insertar":
                insertarPedido(request, response);
                break;
            case "actualizar":
                actualizarPedido(request, response);
                break;
            default:
                listarPedidos(request, response);
                break;
        }
    }
    
    /**
     * Lista todos los pedidos.
     */
    private void listarPedidos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Pedido> listaPedidos = pedidoDAO.obtenerTodosLosPedidos();
        request.setAttribute("listaPedidos", listaPedidos);
        request.getRequestDispatcher("pedidos/lista-pedidos.jsp").forward(request, response);
    }
    
    /**
     * Muestra el formulario para crear un nuevo pedido.
     */
    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Cliente> listaClientes = clienteDAO.obtenerTodosLosClientes();
        List<Producto> listaProductos = productoDAO.obtenerTodosLosProductos();
        request.setAttribute("listaClientes", listaClientes);
        request.setAttribute("listaProductos", listaProductos);
        request.getRequestDispatcher("pedidos/formulario-pedido.jsp").forward(request, response);
    }
    
    /**
     * Muestra el formulario para editar un pedido existente.
     */
    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Pedido pedidoExistente = pedidoDAO.obtenerPedidoPorId(id);
        List<Cliente> listaClientes = clienteDAO.obtenerTodosLosClientes();
        List<Producto> listaProductos = productoDAO.obtenerTodosLosProductos();
        
        request.setAttribute("pedido", pedidoExistente);
        request.setAttribute("listaClientes", listaClientes);
        request.setAttribute("listaProductos", listaProductos);
        request.getRequestDispatcher("pedidos/formulario-pedido.jsp").forward(request, response);
    }
    
    /**
     * Inserta un nuevo pedido en la base de datos.
     */
    private void insertarPedido(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        int clienteId = Integer.parseInt(request.getParameter("clienteId"));
        int productoId = Integer.parseInt(request.getParameter("productoId"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        
        Pedido nuevoPedido = new Pedido(clienteId, productoId, cantidad);
        pedidoDAO.insertarPedido(nuevoPedido);
        response.sendRedirect("pedidos?action=listar");
    }
    
    /**
     * Actualiza un pedido existente.
     */
    private void actualizarPedido(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String estadoStr = request.getParameter("estado");
        
        Pedido.Estado estado = Pedido.Estado.valueOf(estadoStr);
        Pedido pedidoActualizado = new Pedido();
        pedidoActualizado.setId(id);
        pedidoActualizado.setCantidad(cantidad);
        pedidoActualizado.setEstado(estado);
        
        pedidoDAO.actualizarPedido(pedidoActualizado);
        response.sendRedirect("pedidos?action=listar");
    }
    
    /**
     * Elimina un pedido de la base de datos.
     */
    private void eliminarPedido(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        pedidoDAO.eliminarPedido(id);
        response.sendRedirect("pedidos?action=listar");
    }
}