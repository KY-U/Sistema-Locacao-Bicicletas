<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<fmt:bundle basename = "messages">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="clients.page.title" /></title>
</head>
<body>
    <h2><fmt:message key="clients.page.title" /></h2>
    <c:choose>
        <c:when test="${not empty usuario and not empty tipoUsuario}">
            <a href="${pageContext.request.contextPath}/clientes/new"><fmt:message key="clients.new" /></a>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="clients.not.logged" /></p>
        </c:otherwise>
    </c:choose>

    <table border="1">
        <tr>
            <th>Email</th>
            <th>CPF</th>
            <th>Nome</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="cliente" items="${listaClientes}">
            <tr>
                <td>${cliente.email}</td>
                <td>${cliente.cpf}</td>
                <td>${cliente.nome}</td>
                <td>
                    <c:choose>
                        <c:when test="${not empty usuario and not empty tipoUsuario}">
                            <a href="${pageContext.request.contextPath}/clientes/edit?email=${cliente.email}"><fmt:message key="clients.edit" /></a>
                            <a href="${pageContext.request.contextPath}/clientes/delete?email=${cliente.email}"><fmt:message key="clients.delete" /></a>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="clients.actions.unavailable" />
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</fmt:bundle>
</html>

