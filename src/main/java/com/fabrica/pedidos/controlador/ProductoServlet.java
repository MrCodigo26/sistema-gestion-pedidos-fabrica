package com.fabrica.pedidos.controlador;

import com.fabrica.pedidos.dao.ProductoDAO;
import com.fabrica.pedidos.modelo.Producto;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Servlet que maneja el CRUD de Productos.
 */
@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {
    
    private ProductoDAO productoDAO;
    
    @Override
    public void init() {
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
                eliminarProducto(request, response);
                break;
            case "listar":
            default:
                listarProductos(request, response);
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
                insertarProducto(request, response);
                break;
            case "actualizar":
                actualizarProducto(request, response);
                break;
            default:
                listarProductos(request, response);
                break;
        }
    }
    
    /**
     * Lista todos los productos.
     */
    private void listarProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Producto> listaProductos = productoDAO.obtenerTodosLosProductos();
        request.setAttribute("listaProductos", listaProductos);
        request.getRequestDispatcher("productos/lista-productos.jsp").forward(request, response);
    }
    
    /**
     * Muestra el formulario para crear un nuevo producto.
     */
    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher("productos/formulario-producto.jsp").forward(request, response);
    }
    
    /**
     * Muestra el formulario para editar un producto existente.
     */
    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Producto productoExistente = productoDAO.obtenerProductoPorId(id);
        request.setAttribute("producto", productoExistente);
        request.getRequestDispatcher("productos/formulario-producto.jsp").forward(request, response);
    }
    
    /**
     * Inserta un nuevo producto en la base de datos.
     */
    private void insertarProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        BigDecimal precio = new BigDecimal(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String categoria = request.getParameter("categoria");
        
        Producto nuevoProducto = new Producto(nombre, descripcion, precio, stock, categoria);
        productoDAO.insertarProducto(nuevoProducto);
        response.sendRedirect("productos?action=listar");
    }
    
    /**
     * Actualiza un producto existente.
     */
    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        BigDecimal precio = new BigDecimal(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String categoria = request.getParameter("categoria");
        
        Producto productoActualizado = new Producto(nombre, descripcion, precio, stock, categoria);
        productoActualizado.setId(id);
        
        productoDAO.actualizarProducto(productoActualizado);
        response.sendRedirect("productos?action=listar");
    }
    
    /**
     * Elimina un producto de la base de datos.
     */
    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        productoDAO.eliminarProducto(id);
        response.sendRedirect("productos?action=listar");
    }
}