<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Clientes</title>
</head>
<body>
    <h2>Clientes</h2>
    <c:choose>
        <c:when test="${not empty usuario and not empty tipoUsuario}">
            <a href="${pageContext.request.contextPath}/clientes/new">Novo cliente</a>
        </c:when>
        <c:otherwise>
            <p>Para criar, editar ou excluir clientes, você precisa estar logado.</p>
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
                            <a href="${pageContext.request.contextPath}/clientes/edit?email=${cliente.email}">Editar</a>
                            <a href="${pageContext.request.contextPath}/clientes/delete?email=${cliente.email}">Excluir</a>
                        </c:when>
                        <c:otherwise>
                            Ações não disponíveis
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
