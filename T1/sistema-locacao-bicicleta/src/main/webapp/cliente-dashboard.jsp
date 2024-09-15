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
    <h2>Bem-vindo, ${usuario.nome}!</h2>
    
    <!-- Checar se usuário está logado -->
    <c:choose>
        <c:when test="${not empty usuario and not empty tipoUsuario}">
            <h3>Locação de Bicicleta</h3>

            <!-- Form para cadastrar uma locação -->
            <form action="${pageContext.request.contextPath}/locacoes/new" method="get">
                <label for="locadora">Escolha uma locadora:</label>
                <select id="locadora" name="cnpj">
                    <!-- Exibir lista de locadoras, enviando o CNPJ no value -->
                    <c:forEach var="locadora" items="${listaLocadoras}">
                        <option value="${locadora.cnpj}">${locadora.nome}</option>
                    </c:forEach>
                </select>
                <br>
                 <label for="data">Data e hora da locação:</label>
                 <input type="datetime-local" id="data" name="dataHora" required>

                <!-- Campo oculto para enviar o CPF do usuário -->
                <input type="hidden" name="cpf" value="${usuario.cpf}">

                <input type="submit" value="Confirmar Locação">
            </form>
            
            <!-- Mensagem de erro/sucesso -->
            <c:if test="${not empty mensagem}">
                <p>${mensagem}</p>
            </c:if>
        </c:when>
        <c:otherwise>
            <p>Para realizar uma locação, você precisa estar logado.</p>
        </c:otherwise>
    </c:choose>

    <p><a href="${pageContext.request.contextPath}/locacoes/list/cliente">Ver suas locações</a></p>
</body>
</html>