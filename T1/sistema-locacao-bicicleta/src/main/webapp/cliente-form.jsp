<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>
        <c:choose>
            <c:when test="${cliente != null}">Editar Cliente</c:when>
            <c:otherwise>Novo Cliente</c:otherwise>
        </c:choose>
    </title>
</head>
<body>
    <h2><c:choose><c:when test="${cliente != null}">Editar Cliente</c:when><c:otherwise>Novo Cliente</c:otherwise></c:choose></h2>
    <form action="<c:choose><c:when test="${cliente != null}">${pageContext.request.contextPath}/clientes/update</c:when><c:otherwise>${pageContext.request.contextPath}/clientes/insert</c:otherwise></c:choose>" method="get">
        <c:if test="${cliente != null}">
            <input type="hidden" name="emailOriginal" value="${cliente.email}">
        </c:if>
        <p>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${cliente != null ? cliente.email : ''}" required>
        </p>
        <p>
            <label for="senha">Senha:</label>
            <input type="password" id="senha" name="senha" value="${cliente != null ? cliente.senha : ''}" required>
        </p>
        <p>
            <label for="cpf">CPF:</label>
            <input type="text" id="cpf" name="cpf" value="${cliente != null ? cliente.cpf : ''}" required>
        </p>
        <p>
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" value="${cliente != null ? cliente.nome : ''}" required>
        </p>
        <p>
            <label for="telefone">Telefone:</label>
            <input type="text" id="telefone" name="telefone" value="${cliente != null ? cliente.telefone : ''}" required>
        </p>
        <p>
            <label for="sexo">Sexo:</label>
            <input type="text" id="sexo" name="sexo" value="${cliente != null ? cliente.sexo : ''}" required>
        </p>
        <p>
            <label for="dataNascimento">Data de Nascimento:</label>
            <input type="date" id="dataNascimento" name="dataNascimento" value="${cliente != null ? cliente.dataNascimento : ''}" required>
        </p>
        <p>
            <input type="submit" value="Salvar">
        </p>
    </form>
</body>
</html>
