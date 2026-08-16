package com.fabrica.pedidos.controlador;

import com.fabrica.pedidos.dao.UsuarioDAO;
import com.fabrica.pedidos.modelo.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet que maneja el login y logout del sistema.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    private UsuarioDAO usuarioDAO;
    
    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }
    
    /**
     * Muestra la página de login.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Si ya está logueado, redirigir al dashboard
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuario") != null) {
            response.sendRedirect("dashboard");
            return;
        }
        
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    
    /**
     * Procesa el formulario de login.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasena = request.getParameter("contrasena");
        
        Usuario usuario = usuarioDAO.validarLogin(nombreUsuario, contrasena);
        
        if (usuario != null) {
            // Login exitoso
            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", usuario);
            session.setAttribute("rol", usuario.getRol().name());
            
            response.sendRedirect("dashboard");
        } else {
            // Login fallido
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}