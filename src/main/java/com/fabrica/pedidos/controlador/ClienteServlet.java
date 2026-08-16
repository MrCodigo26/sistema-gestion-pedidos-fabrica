package com.fabrica.pedidos.controlador;

import com.fabrica.pedidos.dao.ClienteDAO;
import com.fabrica.pedidos.modelo.Cliente;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet que maneja el CRUD de Clientes.
 */
@WebServlet("/clientes")
public class ClienteServlet extends HttpServlet {
    
    private ClienteDAO clienteDAO;
    
    @Override
    public void init() {
        clienteDAO = new ClienteDAO();
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
                eliminarCliente(request, response);
                break;
            case "listar":
            default:
                listarClientes(request, response);
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
                insertarCliente(request, response);
                break;
            case "actualizar":
                actualizarCliente(request, response);
                break;
            default:
                listarClientes(request, response);
                break;
        }
    }
    
    /**
     * Lista todos los clientes.
     */
    private void listarClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Cliente> listaClientes = clienteDAO.obtenerTodosLosClientes();
        request.setAttribute("listaClientes", listaClientes);
        request.getRequestDispatcher("clientes/lista-clientes.jsp").forward(request, response);
    }
    
    /**
     * Muestra el formulario para crear un nuevo cliente.
     */
    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher("clientes/formulario-cliente.jsp").forward(request, response);
    }
    
    /**
     * Muestra el formulario para editar un cliente existente.
     */
    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente clienteExistente = clienteDAO.obtenerClientePorId(id);
        request.setAttribute("cliente", clienteExistente);
        request.getRequestDispatcher("clientes/formulario-cliente.jsp").forward(request, response);
    }
    
    /**
     * Inserta un nuevo cliente en la base de datos.
     */
    private void insertarCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String documento = request.getParameter("documento");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String email = request.getParameter("email");
        
        Cliente nuevoCliente = new Cliente(nombre, apellido, documento, telefono, direccion, email);
        clienteDAO.insertarCliente(nuevoCliente);
        response.sendRedirect("clientes?action=listar");
    }
    
    /**
     * Actualiza un cliente existente.
     */
    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String documento = request.getParameter("documento");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String email = request.getParameter("email");
        
        Cliente clienteActualizado = new Cliente(nombre, apellido, documento, telefono, direccion, email);
        clienteActualizado.setId(id);
        
        clienteDAO.actualizarCliente(clienteActualizado);
        response.sendRedirect("clientes?action=listar");
    }
    
    /**
     * Elimina un cliente de la base de datos.
     */
    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        clienteDAO.eliminarCliente(id);
        response.sendRedirect("clientes?action=listar");
    }
}