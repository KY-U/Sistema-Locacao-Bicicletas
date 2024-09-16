<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<fmt:bundle basename = "messages">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="locadora.list.title" /></title>
</head>
<body>
    <h2><fmt:message key="locadora.list.title" /></h2>

    <c:choose>
        <c:when test="${not empty usuario and not empty tipoUsuario}">
            <a href="${pageContext.request.contextPath}/locadoras/new"><fmt:message key="locadora.list.create" /></a>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="locadora.list.notLoggedIn" /></p>
        </c:otherwise>
    </c:choose>

    <table border="1">
        <tr>
            <th><fmt:message key="locadora.list.email" /></th>
            <th><fmt:message key="locadora.list.cnpj" /></th>
            <th><fmt:message key="locadora.list.nome" /></th>
            <th><fmt:message key="locadora.list.cidade" /></th>
            <th><fmt:message key="locadora.list.actions" /></th>
        </tr>
        <c:forEach var="locadora" items="${listaLocadoras}">
            <tr>
                <td>${locadora.email}</td>
                <td>${locadora.cnpj}</td>
                <td>${locadora.nome}</td>
                <td>${locadora.cidade}</td>
                <td>
                    <c:choose>
                        <c:when test="${not empty usuario and not empty tipoUsuario}">
                            <a href="${pageContext.request.contextPath}/locadoras/edit?email=${locadora.email}">Editar</a>
                            <a href="${pageContext.request.contextPath}/locadoras/delete?email=${locadora.email}">Excluir</a>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="locadora.list.actionsNotAvailable" />
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</fmt:bundle>
</html>

