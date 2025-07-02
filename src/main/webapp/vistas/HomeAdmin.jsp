<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Habitacion"%>
<%@page import="Modelo.EnumEstadoHabitacion"%>

<%
    List<Habitacion> habitaciones = (List<Habitacion>) request.getAttribute("habitaciones");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Habitaciones</title>
    <script>
        function confirmarEliminacion() {
            return confirm('¿Seguro deseas eliminar esta habitación?');
        }
    </script>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gradient-to-tr from-gray-900 via-indigo-900 to-purple-900 min-h-screen p-8 text-white">

    <div class="max-w-7xl mx-auto">
        <h1 class="text-4xl font-extrabold mb-6">🛏️ Gestión de Habitaciones</h1>

        <div class="mb-6">
            <a href="${pageContext.request.contextPath}/HabitacionController?accion=crear"
               class="inline-block px-6 py-3 bg-gradient-to-r from-pink-500 to-yellow-500 text-white font-bold rounded-lg shadow-lg hover:from-pink-600 hover:to-yellow-600 transition duration-300">
                ➕ Crear nueva habitación
            </a>
        </div>

        <div class="overflow-x-auto rounded-lg shadow-2xl bg-white/10 backdrop-blur-md ring-1 ring-white/20">
            <table class="min-w-full table-auto text-white">
                <thead class="bg-white/10 backdrop-blur-sm text-left text-white uppercase text-sm tracking-wider">
                    <tr>
                        <th class="px-6 py-4">ID</th>
                        <th class="px-6 py-4">Número</th>
                        <th class="px-6 py-4">Tipo</th>
                        <th class="px-6 py-4">Estado</th>
                        <th class="px-6 py-4">Acciones</th>
                    </tr>
                </thead>
                <tbody class="divide-y divide-white/10">
                    <%
                        if (habitaciones != null && !habitaciones.isEmpty()) {
                            for (Habitacion h : habitaciones) {
                    %>
                    <tr class="hover:bg-white/5 transition">
                        <td class="px-6 py-4"><%= h.getIdHabitacion() %></td>
                        <td class="px-6 py-4"><%= h.getNumHabitacion() %></td>
                        <td class="px-6 py-4 capitalize"><%= h.getTipoHabitacion().toString().toLowerCase() %></td>
                        <td class="px-6 py-4">
                            <span class="px-3 py-1 rounded-full text-sm font-semibold 
                                <%= h.getEstado() == EnumEstadoHabitacion.Disponible ? "bg-green-600/80" : "bg-red-600/80" %>">
                                <%= h.getEstado().toString().toLowerCase() %>
                            </span>
                        </td>
                        <td class="px-6 py-4 space-x-2">
                            <a href="${pageContext.request.contextPath}/HabitacionController?accion=ver&id=<%= h.getIdHabitacion() %>"
                               class="px-3 py-1 bg-blue-500 hover:bg-blue-600 rounded-md text-sm font-medium shadow">
                                Ver
                            </a>
                            <a href="${pageContext.request.contextPath}/HabitacionController?accion=editar&id=<%= h.getIdHabitacion() %>"
                               class="px-3 py-1 bg-yellow-500 hover:bg-yellow-600 rounded-md text-sm font-medium shadow">
                                Editar
                            </a>
                            <form action="${pageContext.request.contextPath}/HabitacionController" method="post" class="inline" onsubmit="return confirmarEliminacion()">
    <input type="hidden" name="accion" value="eliminar">
    <input type="hidden" name="id" value="<%= h.getIdHabitacion() %>">
    <button type="submit"
            class="px-3 py-1 bg-red-600 hover:bg-red-700 rounded-md text-sm font-medium shadow">
        Eliminar
    </button>
</form>

                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="5" class="text-center px-6 py-4 text-white/70">
                            No hay habitaciones registradas por ahora.
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>

</body>
</html>
