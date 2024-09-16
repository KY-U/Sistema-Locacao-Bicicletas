<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>

<fmt:bundle basename = "messages">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="rentals.page.title" /></title>
    <script>
        function showAlert(message) {
            if (message) {
                alert(message);
            }
        }
    </script>
</head>
<body onload="showAlert('${sessionScope.mensagem}')">

    <c:choose>
        <c:when test="${not empty locadora}">
            <h2><fmt:message key="rentals.locadora.header"><fmt:param value="${locadora.nome}" /></fmt:message></h2>
        </c:when>
        <c:otherwise>
            <h2><fmt:message key="rentals.usuario.header"><fmt:param value="${usuario.nome}" /></fmt:message></h2>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${not empty locacoes}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Cliente</th>
                        <th>Locadora</th>
                        <th>Data e Hora</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="locacao" items="${locacoes}">
                        <tr>
                            <td>${locacao.cpfCliente}</td>
                            <td>${locacao.cnpjLocadora}</td>
                            <td>${locacao.dataInicio}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <!-- Mensagem diferente dependendo de quem está logado -->
            <c:choose>
                <c:when test="${not empty usuario}">
                    <p><fmt:message key="rentals.no.rentals.client" /></p>
                </c:when>
                <c:when test="${not empty locadora}">
                    <p><fmt:message key="rentals.no.rentals.locadora" /></p>
                </c:when>
            </c:choose>
        </c:otherwise>
    </c:choose>

    <!-- Alterar o link de retorno dependendo de quem está logado -->
    <c:choose>
        <c:when test="${not empty usuario}">
            <p><a href="${pageContext.request.contextPath}/clientes/dashboard"><fmt:message key="rentals.back.to.client.dashboard" /></a></p>
        </c:when>
        <c:when test="${not empty locadora}">
            <p><a href="${pageContext.request.contextPath}/locadoras/dashboard"><fmt:message key="rentals.back.to.locadora.dashboard" /></a></p>
        </c:when>
    </c:choose>

</body>
</fmt:bundle>
</html>

