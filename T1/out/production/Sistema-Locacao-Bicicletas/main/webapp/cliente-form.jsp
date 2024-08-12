<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cliente</title>
</head>
<body>
    <h2><c:if test="${cliente.id == 0}">Novo Cliente</c:if><c:if test="${cliente.id != 0}">Editar Cliente</c:if></h2>
    <form action="clientes/<c:if test="${cliente.id == 0}">insert</c:if><c:if test="${cliente.id != 0}">update</c:if>" method="post">
        <input type="hidden" name="id" value="${cliente.id}"/>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" value="${cliente.email}"/>
        <br/>
        <label for="senha">Senha:</label>
        <input type="password" id="senha" name="senha" value="${cliente.senha}"/>
        <br/>
        <label for="cpf">CPF:</label>
        <input type="text" id="cpf" name="cpf" value="${cliente.cpf}"/>
        <br/>
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" value="${cliente.nome}"/>
        <br/>
        <label for="telefone">Telefone:</label>
        <input type="text" id="telefone" name="telefone" value="${cliente.telefone}"/>
        <br/>
        <label for="sexo">Sexo:</label>
        <input type="text" id="sexo" name="sexo" value="${cliente.sexo}"/>
        <br/>
        <label for="dataNascimento">Data de Nascimento:</label>
        <input type="date" id="dataNascimento" name="dataNascimento" value="${cliente.dataNascimento}"/>
        <br/>
        <input type="submit" value="Salvar"/>
    </form>
    <a href="clientes/list">Cancelar</a>
</body>
</html>