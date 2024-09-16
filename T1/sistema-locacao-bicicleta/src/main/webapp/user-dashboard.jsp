<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>

<fmt:bundle basename = "messages">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="user_dashboard_title"/></title>
</head>
<body>
    <h1><fmt:message key="user_dashboard_title"/></h1>

    <!-- Verificação de Login -->
    <%
        // Verificar se o usuário está logado
        if (session.getAttribute("userEmail") == null) {
            response.sendRedirect("login.jsp"); // Redireciona para a página de login se não estiver logado
            return;
        }
    %>

    <!-- Menu de Navegação -->
    <h2><fmt:message key="menu_header"/></h2>
    <ul>
        <li><a href="user/rentals"><fmt:message key="list_rentals"/></a></li>
        <li><a href="user/rent"><fmt:message key="rent_bike"/></a></li>
    </ul>

    <!-- Seção para Efetuar Locação -->
    <h2><fmt:message key="rent_title"/></h2>
    <form action="user/rent" method="post">
        <label for="locadora"><fmt:message key="locadora_label"/></label>
        <select id="locadora" name="locadora" required>
            <c:forEach var="locadora" items="${locadoras}">
                <option value="${locadora.email}">${locadora.nome}</option>
            </c:forEach>
        </select><br><br>

        <label for="data"><fmt:message key="date_label"/></label>
        <input type="date" id="data" name="data" required><br><br>

        <button type="submit"><fmt:message key="confirm_rent_button"/></button>
    </form>

    <!-- Seção para Buscar Bicicleta -->
    <h2><fmt:message key="search_bike_title"/></h2>
    <form action="cliente/buscaBicicleta" method="get">
        <label for="locadora"><fmt:message key="locadora_label"/></label>
        <select id="locadora" name="locadora" required>
            <c:forEach var="locadora" items="${locadoras}">
                <option value="${locadora.email}">${locadora.nome}</option>
            </c:forEach>
        </select><br><br>

        <label for="modelo"><fmt:message key="bike_model_label"/></label>
        <input type="text" id="modelo" name="modelo" placeholder="<fmt:message key='bike_model_label'/>" required><br><br>

        <button type="submit"><fmt:message key="search_bike_button"/></button>
    </form>

    <!-- Resultados da Busca de Bicicletas -->
    <h2><fmt:message key="search_results_title"/></h2>
    <c:if test="${not empty bicicletas}">
        <table>
            <thead>
                <tr>
                    <th><fmt:message key="locadora_label"/></th>
                    <th><fmt:message key="bike_model_label"/></th>
                    <th><fmt:message key="available_label"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="bicicleta" items="${bicicletas}">
                    <tr>
                        <td>${bicicleta.locadora}</td>
                        <td>${bicicleta.modelo}</td>
                        <td><c:out value="${bicicleta.disponivel ? fmt:message('available_label') : fmt:message('not_available_label')}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
</fmt:bundle>
