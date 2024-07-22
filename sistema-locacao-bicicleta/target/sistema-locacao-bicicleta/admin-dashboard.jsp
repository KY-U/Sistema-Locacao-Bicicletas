<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
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
        // Verificar se o usuário é um administrador
        if (session.getAttribute("userRole") == null || !session.getAttribute("userRole").equals("admin")) {
            response.sendRedirect("login.jsp"); // Redireciona para a página de login se não for admin
            return; // Importante parar a execução do código após o redirecionamento
        }
    %>

    <!-- Menu de Navegação -->
    <h2>Menu</h2>
    <ul>
        <li><a href="admin/clients/list">Listar Clientes</a></li>
        <li><a href="admin/clients/new">Adicionar Novo Cliente</a></li>

        <li><a href="admin/locadoras/list">Listar Locadoras</a></li>
        <li><a href="admin/locadoras/new">Adicionar Nova Locadora</a></li>
    </ul>
</body>
</html>