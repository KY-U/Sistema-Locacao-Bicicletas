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
        <!-- Se o usuário estiver logado -->
        <c:when test="${not empty usuario}">
            <h2>Locações de ${usuario.nome}</h2>
        </c:when>

        <!-- Se a locadora estiver logada -->
        <c:when test="${not empty locadora}">
            <h2>Locações da Locadora ${locadora.nome}</h2>
        </c:when>
    </c:choose>

    <c:choose>
        <c:when test="${not empty locacoes}">
            <table border="1">
                <thead>
                    <tr>
                        <!-- Alterar cabeçalhos de acordo com quem está logado -->
                        <c:choose>
                            <c:when test="${not empty usuario}">
                                <th>Locadora</th>
                            </c:when>
                            <c:when test="${not empty locadora}">
                                <th>Cliente</th>
                            </c:when>
                        </c:choose>
                        <th>Data e Hora</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="locacao" items="${locacoes}">
                        <tr>
                            <!-- Exibir a locadora ou cliente dependendo de quem está logado -->
                            <c:choose>
                                <c:when test="${not empty usuario}">
                                    <td>${locacao.locadora}</td>
                                </c:when>
                                <c:when test="${not empty locadora}">
                                    <td>${locacao.cliente}</td>
                                </c:when>
                            </c:choose>
                            <td>${locacao.dataHora}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <!-- Mensagem diferente dependendo de quem está logado -->
            <c:choose>
                <c:when test="${not empty usuario}">
                    <p>Você ainda não fez nenhuma locação.</p>
                </c:when>
                <c:when test="${not empty locadora}">
                    <p>Esta locadora ainda não possui locações.</p>
                </c:when>
            </c:choose>
        </c:otherwise>
    </c:choose>

    <!-- Alterar o link de retorno dependendo de quem está logado -->
    <c:choose>
        <c:when test="${not empty usuario}">
            <p><a href="cliente-dashboard.jsp">Voltar ao Dashboard do Cliente</a></p>
        </c:when>
        <c:when test="${not empty locadora}">
            <p><a href="locadora-dashboard.jsp">Voltar ao Dashboard da Locadora</a></p>
        </c:when>
    </c:choose>

</body>
</html>
