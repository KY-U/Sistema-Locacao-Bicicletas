<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><c:choose>
            <c:when test="${locadora != null}">
                Editar Locadora
            </c:when>
            <c:otherwise>
                Cadastrar Locadora
            </c:otherwise>
        </c:choose></title>
</head>
<body>

<h1><c:choose>
        <c:when test="${locadora != null}">
            Editar Locadora
        </c:when>
        <c:otherwise>
            Cadastrar Locadora
        </c:otherwise>
    </c:choose></h1>

<form action="<c:choose>
        <c:when test="${locadora != null}">
            ${pageContext.request.contextPath}/locadoras/update
        </c:when>
        <c:otherwise>
            ${pageContext.request.contextPath}/locadoras/insert
        </c:otherwise>
    </c:choose>" method="get">
    
    <label for="email">Email:</label>
    <input type="text" id="email" name="email" value="${locadora != null ? locadora.email : ''}" required/>

    <label for="senha">Senha:</label>
    <input type="password" id="senha" name="senha" value="${locadora != null ? locadora.senha : ''}" required/>

    <label for="cnpj">CNPJ:</label>
    <input type="text" id="cnpj" name="cnpj" value="${locadora != null ? locadora.cnpj : ''}" required/>

    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" value="${locadora != null ? locadora.nome : ''}" required/>

    <label for="cidade">Cidade:</label>
    <input type="text" id="cidade" name="cidade" value="${locadora != null ? locadora.cidade : ''}" required/>

    <input type="submit" value="<c:choose>
        <c:when test="${locadora != null}">
            Atualizar
        </c:when>
        <c:otherwise>
            Cadastrar
        </c:otherwise>
    </c:choose>"/>
</form>

</body>
</html>