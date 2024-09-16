<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<fmt:bundle basename = "messages">
<head>
    <meta charset="UTF-8">
    <title><c:out value="${pageContext.request.locale eq 'pt' ? 'admin.dashboard.title' : 'admin.dashboard.title'}"/></title>
</head>
<body>
    <h1><c:out value="${pageContext.request.locale eq 'pt' ? 'admin.dashboard.title' : 'admin.dashboard.title'}"/></h1>

    <!-- Verificação de Login -->
    <%
        if (session.getAttribute("tipoUsuario") == null || !session.getAttribute("tipoUsuario").equals("admin")) {
            response.sendRedirect("login.jsp"); // Redireciona para a página de login se não for admin
            return; // Importante parar a execução do código após o redirecionamento
        }
    %>

    <!-- Menu de Navegação -->
    <h2>Menu</h2>
    <ul>
        <li><a href="clientes"><c:out value="${pageContext.request.locale eq 'pt' ? 'admin.dashboard.menu.clients' : 'admin.dashboard.menu.clients'}"/></a></li>
        <li><a href="locadoras"><c:out value="${pageContext.request.locale eq 'pt' ? 'admin.dashboard.menu.rentals' : 'admin.dashboard.menu.rentals'}"/></a></li>
    </ul>
</body>
</fmt:bundle>
</html>
