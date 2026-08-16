package com.fabrica.pedidos.controlador;

import com.fabrica.pedidos.dao.UsuarioDAO;
import com.fabrica.pedidos.modelo.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet que maneja el CRUD de Usuarios.
 */
@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {
    
    private UsuarioDAO usuarioDAO;
    
    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
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
                eliminarUsuario(request, response);
                break;
            case "listar":
            default:
                listarUsuarios(request, response);
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
                insertarUsuario(request, response);
                break;
            case "actualizar":
                actualizarUsuario(request, response);
                break;
            default:
                listarUsuarios(request, response);
                break;
        }
    }
    
    /**
     * Lista todos los usuarios.
     */
    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Usuario> listaUsuarios = usuarioDAO.obtenerTodosLosUsuarios();
        request.setAttribute("listaUsuarios", listaUsuarios);
        request.getRequestDispatcher("usuarios/lista-usuarios.jsp").forward(request, response);
    }
    
    /**
     * Muestra el formulario para crear un nuevo usuario.
     */
    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher("usuarios/formulario-usuario.jsp").forward(request, response);
    }
    
    /**
     * Muestra el formulario para editar un usuario existente.
     */
    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuarioExistente = usuarioDAO.obtenerUsuarioPorId(id);
        request.setAttribute("usuario", usuarioExistente);
        request.getRequestDispatcher("usuarios/formulario-usuario.jsp").forward(request, response);
    }
    
    /**
     * Inserta un nuevo usuario en la base de datos.
     */
    private void insertarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasena = request.getParameter("contrasena");
        String rolStr = request.getParameter("rol");
        String email = request.getParameter("email");
        
        Usuario.Rol rol = Usuario.Rol.valueOf(rolStr);
        Usuario nuevoUsuario = new Usuario(nombreUsuario, contrasena, rol, email);
        
        usuarioDAO.insertarUsuario(nuevoUsuario);
        response.sendRedirect("usuarios?action=listar");
    }
    
    /**
     * Actualiza un usuario existente.
     */
    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasena = request.getParameter("contrasena");
        String rolStr = request.getParameter("rol");
        String email = request.getParameter("email");
        
        Usuario.Rol rol = Usuario.Rol.valueOf(rolStr);
        Usuario usuarioActualizado = new Usuario(nombreUsuario, contrasena, rol, email);
        usuarioActualizado.setId(id);
        
        usuarioDAO.actualizarUsuario(usuarioActualizado);
        response.sendRedirect("usuarios?action=listar");
    }
    
    /**
     * Elimina un usuario de la base de datos.
     */
    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        usuarioDAO.eliminarUsuario(id);
        response.sendRedirect("usuarios?action=listar");
    }
}