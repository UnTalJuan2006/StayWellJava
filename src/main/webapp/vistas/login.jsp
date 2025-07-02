

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
     <div class="container">
    <!-- LADO IZQUIERDO -->
    <div class="left-side">
      <div class="logo-box">
        <img src="" alt="Logo Empresa">
      </div>
    </div>

    <!-- LADO DERECHO -->
    <div class="right-side">
      <div class="login-box">
          
          
         <form action="${pageContext.request.contextPath}/UsuarioController" method="post">
        <input type="hidden" name="accion" value="login" />
          <h2>Login</h2>

          <div class="input-box">
        <span class="icon"><ion-icon name="mail-outline"></ion-icon></span>
        <input type="email" name="email" required placeholder=" ">
        <label>Email</label>
    </div>

    <div class="input-box">
        <span class="icon"><ion-icon name="lock-closed-outline"></ion-icon></span>
        <input type="password" name="password" required placeholder=" ">
        <label>Password</label>
    </div>

    <label><input type="checkbox"> Remember</label>
    <a href="#">Forgot password?</a>

    <button type="submit">Login</button>

    <div class="register-link">
        <p>Don’t have an account? <a href="registro.jsp">Register</a></p>
    </div>
</form>
      </div>
    </div>
  <!-- Ionicons -->
  <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
  <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>

</body>
</html>