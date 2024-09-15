<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Locadoras</title>
</head>
<body>
    <h2>Locadoras</h2>
    
    <c:choose>
        <c:when test="${not empty usuario and not empty tipoUsuario}">
            <a href="${pageContext.request.contextPath}/locadoras/new">Nova Locadora</a>
        </c:when>
        <c:otherwise>
            <p>Para criar, editar ou excluir locadoras, você precisa estar logado.</p>
        </c:otherwise>
    </c:choose>
    
    <table border="1">
        <tr>
            <th>Email</th>
            <th>CNPJ</th>
            <th>Nome</th>
            <th>Cidade</th>
            <th>Ações</th>
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
                            Ações não disponíveis
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
