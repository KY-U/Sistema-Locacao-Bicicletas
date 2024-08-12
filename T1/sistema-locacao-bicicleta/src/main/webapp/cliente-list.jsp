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
                    <form action="clientes/edit" method="get" style="display:inline;">
                        <input type="hidden" name="email" value="${cliente.email}">
                        <input type="submit" value="Editar">
                    </form>
                    <form action="clientes/delete" method="get" style="display:inline;">
                        <input type="hidden" name="email" value="${cliente.email}">
                        <input type="submit" value="Excluir">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
