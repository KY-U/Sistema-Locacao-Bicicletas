<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<fmt:bundle basename = "messages">
<head>
    <meta charset="UTF-8">
    <title>
        <c:choose>
            <c:when test="${locadora != null}">
                <fmt:message key="locadora.form.title.edit" />
            </c:when>
            <c:otherwise>
                <fmt:message key="locadora.form.title.create" />
            </c:otherwise>
        </c:choose>
    </title>
</head>
<body>

<h1>
    <c:choose>
        <c:when test="${locadora != null}">
            <fmt:message key="locadora.form.title.edit" />
        </c:when>
        <c:otherwise>
            <fmt:message key="locadora.form.title.create" />
        </c:otherwise>
    </c:choose>
</h1>

<form action="<c:choose>
        <c:when test="${locadora != null}">
            ${pageContext.request.contextPath}/locadoras/update
        </c:when>
        <c:otherwise>
            ${pageContext.request.contextPath}/locadoras/insert
        </c:otherwise>
    </c:choose>" method="get">

    <label for="email"><fmt:message key="locadora.form.email" /></label>
    <input type="text" id="email" name="email" value="${locadora != null ? locadora.email : ''}" required/>

    <label for="senha"><fmt:message key="locadora.form.senha" /></label>
    <input type="password" id="senha" name="senha" value="${locadora != null ? locadora.senha : ''}" required/>

    <label for="cnpj"><fmt:message key="locadora.form.cnpj" /></label>
    <input type="text" id="cnpj" name="cnpj" value="${locadora != null ? locadora.cnpj : ''}" required/>

    <label for="nome"><fmt:message key="locadora.form.nome" /></label>
    <input type="text" id="nome" name="nome" value="${locadora != null ? locadora.nome : ''}" required/>

    <label for="cidade"><fmt:message key="locadora.form.cidade" /></label>
    <input type="text" id="cidade" name="cidade" value="${locadora != null ? locadora.cidade : ''}" required/>

    <input type="submit" value="<c:choose>
        <c:when test="${locadora != null}">
            <fmt:message key="locadora.form.submit.update" />
        </c:when>
        <c:otherwise>
            <fmt:message key="locadora.form.submit.create" />
        </c:otherwise>
    </c:choose>"/>
</form>

</body>
</fmt:bundle>
</html>
