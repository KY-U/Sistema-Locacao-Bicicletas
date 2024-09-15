<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
</head>
<body>
    <h1>Admin Dashboard</h1>

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
        <li><a href="/clientes/list">Clientes</a></li>
        <li><a href="/locadoras/list">Locadoras</a></li>
    </ul>
</body>
</html>