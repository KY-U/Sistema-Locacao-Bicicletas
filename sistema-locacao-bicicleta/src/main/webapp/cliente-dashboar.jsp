<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard do Cliente</title>
</head>
<body>
    <h2>Bem-vindo, ${usuario}!</h2>
    
    <!-- Check if the user is logged in -->
    <c:choose>
        <c:when test="${not empty usuario and not empty tipoUsuario}">
            <h3>Locação de Bicicleta</h3>
            
            <!-- Form to register a rental -->
            <form action="rent" method="post">
                <label for="locadora">Escolha uma locadora:</label>
                <select id="locadora" name="locadora">
                    <c:forEach var="locadora" items="${listaLocadoras}">
                        <option value="${locadora.email}">${locadora.nome}</option>
                    </c:forEach>
                </select>
                
                <label for="data">Data da locação:</label>
                <input type="date" id="data" name="data" required>
                
                <input type="submit" value="Cadastrar Locação">
            </form>
            
            <!-- Optional: Display any success or error messages -->
            <c:if test="${not empty mensagem}">
                <p>${mensagem}</p>
            </c:if>
        </c:when>
        <c:otherwise>
            <p>Para realizar uma locação, você precisa estar logado.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>