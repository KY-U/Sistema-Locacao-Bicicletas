<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<fmt:bundle basename = "messages">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="login.title" /></title>
</head>
<body>
    <h2><fmt:message key="login.title" /></h2>
    <form action="login" method="post">
        <label for="email"><fmt:message key="login.email" />:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="senha"><fmt:message key="login.password" />:</label>
        <input type="password" id="senha" name="senha" required><br><br>

        <button type="submit"><fmt:message key="login.submit" /></button>
    </form>

    <form action="locale" method="get">
        <button type="submit" name="lang" value="pt">Português</button>
        <button type="submit" name="lang" value="en">English</button>
    </form>
</body>
</fmt:bundle>
</html>