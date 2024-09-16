<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<fmt:bundle basename = "messages">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="client.dashboard.title" /></title>
</head>
<body>
    <h2><fmt:message key="client.dashboard.welcome" /></h2>

    <!-- Checar se usuário está logado -->
    <c:choose>
        <c:when test="${not empty usuario and not empty tipoUsuario}">
            <h3><fmt:message key="client.dashboard.rent.title" /></h3>

            <!-- Form para cadastrar uma locação -->
            <form action="${pageContext.request.contextPath}/locacoes/new" method="get">
                <label for="locadora"><fmt:message key="client.dashboard.select.provider" /></label>
                <select id="locadora" name="cnpj">
                    <!-- Exibir lista de locadoras, enviando o CNPJ no value -->
                    <c:forEach var="locadora" items="${listaLocadoras}">
                        <option value="${locadora.cnpj}">${locadora.nome}</option>
                    </c:forEach>
                </select>
                <br>
                <label for="data"><fmt:message key="client.dashboard.rent.date" /></label>
                <input type="datetime-local" id="data" name="dataHora" required>

                <!-- Campo oculto para enviar o CPF do usuário -->
                <input type="hidden" name="cpf" value="${usuario.cpf}">

                <input type="submit" value="<fmt:message key="client.dashboard.submit.rent" />">
            </form>

            <!-- Mensagem de erro/sucesso -->
            <c:if test="${not empty mensagem}">
                <p>${mensagem}</p>
            </c:if>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="client.dashboard.login.required" /></p>
        </c:otherwise>
    </c:choose>

    <p><a href="${pageContext.request.contextPath}/locacoes/list/cliente"><fmt:message key="client.dashboard.view.rentals" /></a></p>
</body>
</fmt:bundle>
</html>
