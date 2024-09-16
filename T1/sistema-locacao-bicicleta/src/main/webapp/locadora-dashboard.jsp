<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<fmt:bundle basename = "messages">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="locadora.dashboard.title" /></title>
</head>
<body>
    <h2><fmt:message key="locadora.dashboard.welcome"><fmt:param value="${locadora.nome}" /></fmt:message></h2>

    <!-- Mostra o CNPJ da locadora -->
    <p><strong><fmt:message key="locadora.dashboard.cnpj" /></strong> ${locadora.cnpj}</p>

    <!-- Link para a lista de locações da locadora -->
    <p><a href="${pageContext.request.contextPath}/locacoes/list/locadora"><fmt:message key="locadora.dashboard.view.rentals" /></a></p>

    <!-- Outros links ou ações que a locadora pode realizar -->
    <!--
    <ul>
        <li><a href="${pageContext.request.contextPath}/locadora/perfil"><fmt:message key="locadora.dashboard.view.profile" /></a></li>
        <li><a href="${pageContext.request.contextPath}/logout"><fmt:message key="locadora.dashboard.logout" /></a></li>
    </ul>
    -->
</body>
</fmt:bundle>
</html>

