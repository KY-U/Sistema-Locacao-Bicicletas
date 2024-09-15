<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard da Locadora</title>
</head>
<body>
    <h2>Bem-vindo, ${locadora.nome}</h2>

    <!-- Mostra o CNPJ da locadora -->
    <p><strong>CNPJ:</strong> ${locadora.cnpj}</p>

    <!-- Link para a lista de locações da locadora -->
    <p><a href="${pageContext.request.contextPath}/locacoes/list/locadora">Ver todas as locações</a></p>

    <!-- Outros links ou ações que a locadora pode realizar
    <ul>
        <li><a href="${pageContext.request.contextPath}/locadora/perfil">Ver Perfil</a></li>
        <li><a href="${pageContext.request.contextPath}/logout">Sair</a></li>
    </ul>
    -->
</body>
</html>
