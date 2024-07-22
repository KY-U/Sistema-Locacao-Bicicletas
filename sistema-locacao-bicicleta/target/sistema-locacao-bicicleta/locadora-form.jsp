<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><c:choose>
            <c:when test="${not empty locadora}">
                Editar Locadora
            </c:when>
            <c:otherwise>
                Cadastrar Locadora
            </c:otherwise>
        </c:choose></title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        form {
            max-width: 600px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        label {
            display: block;
            margin-bottom: 10px;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<h1><c:choose>
        <c:when test="${not empty locadora}">
            Editar Locadora
        </c:when>
        <c:otherwise>
            Cadastrar Locadora
        </c:otherwise>
    </c:choose></h1>

<form action="<c:choose>
        <c:when test="${not empty locadora}">
            ${pageContext.request.contextPath}/locadora/update
        </c:when>
        <c:otherwise>
            ${pageContext.request.contextPath}/locadora/create
        </c:otherwise>
    </c:choose>" method="post">
    <input type="hidden" name="email" value="${not empty locadora ? locadora.email : ''}"/>

    <label for="email">Email:</label>
    <input type="text" id="email" name="email" value="${not empty locadora ? locadora.email : ''}" required/>

    <label for="senha">Senha:</label>
    <input type="password" id="senha" name="senha" value="${not empty locadora ? locadora.senha : ''}" required/>

    <label for="cnpj">CNPJ:</label>
    <input type="text" id="cnpj" name="cnpj" value="${not empty locadora ? locadora.cnpj : ''}" required/>

    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" value="${not empty locadora ? locadora.nome : ''}" required/>

    <label for="cidade">Cidade:</label>
    <input type="text" id="cidade" name="cidade" value="${not empty locadora ? locadora.cidade : ''}" required/>

    <input type="submit" value="<c:choose>
        <c:when test="${not empty locadora}">
            Atualizar
        </c:when>
        <c:otherwise>
            Cadastrar
        </c:otherwise>
    </c:choose>"/>
</form>

</body>
</html>