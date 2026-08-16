<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Sistema de Gestión de Pedidos</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <div class="login-contenedor">
        <div class="login-caja">
            <h1 class="login-titulo"> Sistema de Gestión de Pedidos para Fábrica</h1>
            
            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
            <div class="mensaje-error">
                <%= error %>
            </div>
            <%
                }
            %>
            
            <form action="login" method="post">
                <div class="form-grupo">
                    <label for="nombreUsuario">Usuario:</label>
                    <input type="text" id="nombreUsuario" name="nombreUsuario" required>
                </div>
                
                <div class="form-grupo">
                    <label for="contrasena">Contraseña:</label>
                    <input type="password" id="contrasena" name="contrasena" required>
                </div>
                
                <button type="submit" class="btn btn-verde" style="width: 100%; padding: 12px;">
                    Ingresar al Sistema
                </button>
            </form>
        </div>
    </div>
</body>
</html>