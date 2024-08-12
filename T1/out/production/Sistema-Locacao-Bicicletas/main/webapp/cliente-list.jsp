<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Clientes</title>
</head>
<body>
    <h2>Clientes</h2>
    <a href="clientes/new">Novo Cliente</a>
    <table border="1">
        <tr>
            <th>Email</th>
            <th>CPF</th>
            <th>Nome</th>
            <th>Ações</th>
        </tr>
        <c:forEach var="cliente" items="${listaClientes}">
            <tr>
                <td>${cliente.email}</td>
                <td>${cliente.cpf}</td>
                <td>${cliente.nome}</td>
                <td>
                    <a href="clientes/edit?email=${cliente.email}">Editar</a>
                    <a href="clientes/delete?email=${cliente.email}">Excluir</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>