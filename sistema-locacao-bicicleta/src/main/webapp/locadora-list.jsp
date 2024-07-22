<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Locadoras</title>
</head>
<body>
    <h2>Locadoras</h2>
    <a href="locadoras/new">Nova Locadora</a>
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
                    <a href="locadoras/edit?email=${locadora.email}">Editar</a>
                    <a href="locadoras/delete?email=${locadora.email}">Excluir</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>