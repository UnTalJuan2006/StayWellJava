<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.EnumEstadoHabitacion"%>
<%@page import="Modelo.EnumTipoHabitacion"%>

<%
    EnumEstadoHabitacion[] estados = (EnumEstadoHabitacion[]) request.getAttribute("estados");
    EnumTipoHabitacion[] tipos = (EnumTipoHabitacion[]) request.getAttribute("tipos");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear Habitación</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gradient-to-br from-gray-900 via-indigo-900 to-purple-900 min-h-screen flex items-center justify-center text-white">

    <div class="bg-white/10 p-10 rounded-lg shadow-xl backdrop-blur-md w-full max-w-xl">
        <h1 class="text-3xl font-bold mb-6 text-center">➕ Crear Nueva Habitación</h1>

        <form action="${pageContext.request.contextPath}/HabitacionController" method="post" class="space-y-6">
            <input type="hidden" name="accion" value="registrar" />

            <div>
                <label for="numHabitacion" class="block mb-1 font-semibold">Número de Habitación</label>
                <input type="number" name="numHabitacion" id="numHabitacion" required
                       class="w-full px-4 py-2 rounded-lg text-gray-900 focus:outline-none focus:ring-2 focus:ring-indigo-400">
            </div>

            <div>
                <label for="tipo" class="block mb-1 font-semibold">Tipo de Habitación</label>
                <select name="tipo" id="tipo" required
                        class="w-full px-4 py-2 rounded-lg text-gray-900 focus:outline-none focus:ring-2 focus:ring-indigo-400">
                    <option value="" disabled selected>Seleccione un tipo</option>
                    <%
                        if (tipos != null) {
                            for (EnumTipoHabitacion t : tipos) {
                    %>
                        <option value="<%= t.name() %>"><%= t.name().toLowerCase().replace("_", " ") %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <div>
                <label for="estado" class="block mb-1 font-semibold">Estado</label>
                <select name="estado" id="estado" required
                        class="w-full px-4 py-2 rounded-lg text-gray-900 focus:outline-none focus:ring-2 focus:ring-indigo-400">
                    <%
                        if (estados != null) {
                            for (EnumEstadoHabitacion e : estados) {
                    %>
                        <option value="<%= e.name() %>"><%= e.name().toLowerCase() %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <div class="flex justify-between mt-6">
                <a href="${pageContext.request.contextPath}/HabitacionController?accion=listar"
                   class="px-5 py-2 bg-gray-600 hover:bg-gray-700 rounded-lg font-semibold shadow">
                    ⬅️ Regresar
                </a>
                <button type="submit"
                        class="px-6 py-2 bg-green-500 hover:bg-green-600 rounded-lg font-semibold shadow">
                    💾 Guardar
                </button>
            </div>
        </form>
    </div>

</body>
</html>
