<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registro de Usuario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form-container {
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 0 10px rgba(0,0,0,0.2);
            width: 350px;
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"], 
        select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 8px;
            border: 1px solid #ccc;
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-weight: bold;
        }

        button:hover {
            background-color: #0056b3;
        }

        .mensaje-error {
            color: red;
            text-align: center;
            margin-bottom: 10px;
        }

        .mensaje-exito {
            color: green;
            text-align: center;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Registro de Usuario</h2>

    <%-- Mensaje de error o éxito --%>
    <%
        String error = request.getParameter("error");
        String success = request.getParameter("success");
        if ("true".equals(error)) {
    %>
        <div class="mensaje-error">Error al registrar. Intenta de nuevo.</div>
    <%
        } else if ("true".equals(success)) {
    %>
        <div class="mensaje-exito">Registro exitoso. ¡Inicia sesión!</div>
    <%
        }
    %>

    <form action="../UsuarioController" method="post">
        <input type="hidden" name="accion" value="registrar" />

        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" id="nombre" required>

        <label for="email">Correo:</label>
        <input type="email" name="email" id="email" required>

        <label for="password">Contraseña:</label>
        <input type="password" name="password" id="password" required>

        <%-- Si deseas permitir que el usuario elija el rol --%>
        <!--
        <label for="rol">Rol:</label>
        <select name="rol" id="rol">
            <option value="USER">Usuario</option>
            <option value="ADMIN">Administrador</option>
        </select>
        -->

        <button type="submit">Registrar</button>
    </form>

    <p style="text-align:center; margin-top:15px;">
        ¿Ya tienes cuenta? <a href="login.jsp">Inicia sesión</a>
    </p>
</div>
</body>
</html>