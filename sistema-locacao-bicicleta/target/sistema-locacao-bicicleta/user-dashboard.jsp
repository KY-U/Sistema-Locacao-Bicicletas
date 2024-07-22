<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
</head>
<body>
    <h1>User Dashboard</h1>

    <!-- Verificação de Login -->
    <%
        // Verificar se o usuário está logado
        if (session.getAttribute("userEmail") == null) {
            response.sendRedirect("login.jsp"); // Redireciona para a página de login se não estiver logado
            return;
        }
    %>

    <!-- Menu de Navegação -->
    <h2>Menu</h2>
    <ul>
        <li><a href="user/rentals">Listar Minhas Locações</a></li>
        <li><a href="user/rent">Efetuar Locação</a></li>
    </ul>

    <!-- Seção para Efetuar Locação -->
    <h2>Efetuar Locação</h2>
    <form action="user/rent" method="post">
        <label for="locadora">Locadora:</label>
        <select id="locadora" name="locadora" required>
            <c:forEach var="locadora" items="${locadoras}">
                <option value="${locadora.email}">${locadora.nome}</option>
            </c:forEach>
        </select><br><br>

        <label for="data">Data:</label>
        <input type="date" id="data" name="data" required><br><br>

        <button type="submit">Confirmar Locação</button>
    </form>

    <!-- Seção para Buscar Bicicleta -->
    <h2>Buscar Bicicleta em Locadora</h2>
    <form action="cliente/buscaBicicleta" method="get">
        <label for="locadora">Locadora:</label>
        <select id="locadora" name="locadora" required>
            <c:forEach var="locadora" items="${locadoras}">
                <option value="${locadora.email}">${locadora.nome}</option>
            </c:forEach>
        </select><br><br>

        <label for="modelo">Modelo da Bicicleta:</label>
        <input type="text" id="modelo" name="modelo" placeholder="Digite o modelo da bicicleta" required><br><br>

        <button type="submit">Buscar Bicicleta</button>
    </form>

    <!-- Resultados da Busca de Bicicletas -->
    <h2>Resultados da Busca de Bicicletas</h2>
    <c:if test="${not empty bicicletas}">
        <table>
            <thead>
                <tr>
                    <th>Locadora</th>
                    <th>Modelo</th>
                    <th>Disponível</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="bicicleta" items="${bicicletas}">
                    <tr>
                        <td>${bicicleta.locadora}</td>
                        <td>${bicicleta.modelo}</td>
                        <td>${bicicleta.disponivel ? 'Sim' : 'Não'}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>