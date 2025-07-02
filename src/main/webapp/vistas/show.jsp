<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Habitacion"%>

<%
    Habitacion habitacion = (Habitacion) request.getAttribute("habitacion");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalles de Habitación</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gradient-to-br from-gray-900 via-indigo-900 to-purple-900 min-h-screen flex items-center justify-center text-white">

    <div class="bg-white/10 p-10 rounded-lg shadow-xl backdrop-blur-md w-full max-w-xl">
        <h1 class="text-3xl font-bold mb-6 text-center">🛏️ Detalles de Habitación</h1>

        <div class="space-y-4 text-white text-lg">
            <p><strong>ID:</strong> <%= habitacion.getIdHabitacion() %></p>
            <p><strong>Número:</strong> <%= habitacion.getNumHabitacion() %></p>
            <p><strong>Tipo:</strong> <%= habitacion.getTipoHabitacion().toString().toLowerCase().replace("_", " ") %></p>
            <p><strong>Estado:</strong> <%= habitacion.getEstado().toString().toLowerCase() %></p>
            <p><strong>Fecha de creación:</strong> <%= habitacion.getFechaCreacion() %></p>
            <p><strong>Última actualización:</strong> <%= habitacion.getFechaActualizacion() %></p>
        </div>

        <div class="mt-8 text-center">
            <a href="${pageContext.request.contextPath}/HabitacionController?accion=listar"
               class="px-5 py-2 bg-gray-600 hover:bg-gray-700 rounded-lg font-semibold shadow">
                ⬅️ Regresar al listado
            </a>
        </div>
    </div>

</body>
</html>
