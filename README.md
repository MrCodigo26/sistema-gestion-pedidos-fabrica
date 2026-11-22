# Sistema de Gestion de Pedidos para Fabrica

Sistema web para la gestion de pedidos de una fabrica, permitiendo administrar usuarios, clientes, productos y pedidos con control de roles y estados.

## Tecnologias utilizadas

- Java 11
- JDBC
- Servlets
- JSP
- CSS
- MySQL
- Apache Tomcat 9
- Git
- MySQL Connector J 8.0.33

## Estructura del proyecto

- src/main/java/com/fabrica/pedidos/modelo - Clases entidad
- src/main/java/com/fabrica/pedidos/dao - Clases de acceso a datos
- src/main/java/com/fabrica/pedidos/controlador - Servlets
- src/main/java/com/fabrica/pedidos/util - Clases de utilidad
- src/main/webapp/css - Estilos personalizados
- src/main/webapp/usuarios - Vistas de usuarios
- src/main/webapp/clientes - Vistas de clientes
- src/main/webapp/productos - Vistas de productos
- src/main/webapp/pedidos - Vistas de pedidos
- src/main/webapp/login.jsp - Pagina de inicio de sesion
- src/main/webapp/dashboard.jsp - Panel principal

## Estandares de codificacion

- Clases: PascalCase (Ej: UsuarioDAO)
- Metodos: camelCase (Ej: obtenerUsuarioPorId)
- Variables: camelCase (Ej: nombreUsuario)
- Paquetes: Minusculas (Ej: com.fabrica.pedidos.modelo)
- Constantes: MAYUSCULAS con guiones bajos (Ej: SQL_INSERTAR)

## Base de datos

### Nombre de la base de datos

fabrica_pedidos

### Tablas

- usuarios: id, nombre_usuario, contrasena, rol, email, fecha_registro
- clientes: id, nombre, apellido, documento, telefono, direccion, email, fecha_registro
- productos: id, nombre, descripcion, precio, stock, categoria
- pedidos: id, cliente_id, producto_id, cantidad, fecha_pedido, estado, operador_id, despachador_id

### Roles de usuario

- ADMIN: Acceso total al sistema
- OPERADOR: Gestiona pedidos en proceso
- DESPACHADOR: Gestiona pedidos despachados
- CLIENTE: Realiza pedidos

### Estados de pedido

- PENDIENTE: Pedido creado
- EN_PROCESO: En fabricacion
- DESPACHADO: Enviado al cliente
- ENTREGADO: Recibido por el cliente

### Credenciales de conexion

- URL: jdbc:mysql://localhost:3306/fabrica_pedidos
- Usuario: root
- Contrasena: admin

## Instalacion

### Requisitos

- Java 11 o superior
- Apache Tomcat 9
- MySQL 8.0 o superior
- Eclipse IDE for Enterprise Java
- Conector MySQL JDBC 8.0.33

### Pasos de configuracion

1. Clonar el repositorio
2. Crear la base de datos en MySQL
3. Ejecutar el script SQL para crear las tablas y datos de prueba
4. Agregar el conector mysql-connector-j-8.0.33.jar a WEB-INF/lib
5. Configurar credenciales en ConexionBD.java
6. Desplegar en Tomcat desde Eclipse
7. Acceder a: http://localhost:8080/sistema-gestion-pedidos-fabrica/login

### Usuarios de prueba

- Administrador: Administrador / admin123
- Operador: Operador1 / operador123
- Despachador: Despachador1 / despachador123

## Funcionalidades

- Login con validacion de usuarios
- Dashboard con navegacion
- CRUD completo de Usuarios
- CRUD completo de Clientes
- CRUD completo de Productos
- CRUD completo de Pedidos
- Cambio de estados en pedidos
- Calculo automatico de totales
- Interfaz responsive con CSS personalizado

## Autor

- Felipe Rojas Mestre
- Ficha - 3388965
- Analisis y Desarrollo de Software
- SENA - 2026

## Evidencias

- GA7-220501096-AA2-EV01: Codificacion de modulos del software
- GA7-220501096-AA2-EV02: Modulos de software codificados y probados
