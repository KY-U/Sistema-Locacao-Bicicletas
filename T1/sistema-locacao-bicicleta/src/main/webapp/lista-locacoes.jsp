<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Suas Locações</title>
</head>
<body>
    <h2>Locações de ${usuario.nome}</h2>

    <c:choose>
        <c:when test="${not empty locacoes}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Locadora</th>
                        <th>Data e Hora</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="locacao" items="${locacoes}">
                        <tr>
                            <td>${locacao.locadora}</td>
                            <td>${locacao.dataHora}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>Você ainda não fez nenhuma locação.</p>
        </c:otherwise>
    </c:choose>

    <p><a href="cliente-dashboard.jsp">Voltar ao Dashboard</a></p>
</body>
</html>
}
