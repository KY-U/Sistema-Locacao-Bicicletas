<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Suas Locações</title>
</head>
<body>

    <c:choose>
        <c:when test="${not empty locadora}">
            <h2>Locações da Locadora ${locadora.nome}</h2>
        </c:when>
        <c:otherwise>
            <h2>Locações de ${usuario.nome}</h2>
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
                <c:when test="${empty usuario}">
                    <p>Você ainda não fez nenhuma locação.</p>
                </c:when>
                <c:when test="${empty locadora}">
                    <p>Esta locadora ainda não possui locações.</p>
                </c:when>
            </c:choose>
        </c:otherwise>
    </c:choose>

    <!-- Alterar o link de retorno dependendo de quem está logado -->
    <c:choose>
        <c:when test="${not empty usuario}">
            <p><a href="${pageContext.request.contextPath}/clientes/dashboard">Voltar ao Dashboard do Cliente</a></p>
        </c:when>
        <c:when test="${not empty locadora}">
            <p><a href="${pageContext.request.contextPath}/locadoras/dashboard">Voltar ao Dashboard da Locadora</a></p>
        </c:when>
    </c:choose>

</body>
</html>
