<%@ page import="java.sql.*" %>
<%@ page import="config.Conexion" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Prueba de Conexi�n</title>
</head>
<body>
    <h2>Verificaci�n de conexi�n a la base de datos</h2>
    <%
        Connection conn = null;
        try {
            conn = Conexion.getConnection();
            if (conn != null) {
                out.println("<p style='color:green;'>? Conexi�n exitosa a la base de datos 'staywell'</p>");
            } else {
                out.println("<p style='color:red;'>? No se pudo obtener la conexi�n</p>");
            }
        } catch (Exception e) {
            out.println("<p style='color:red;'>? Error de conexi�n: " + e.getMessage() + "</p>");
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ex) {}
        }
    %>
</body>
</html>
