<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<fmt:bundle basename = "messages">
<fmt:bundle basename = "messages">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="welcome.title" /></title>
</head>
<body>
    <h1><fmt:message key="welcome.title" /></h1>

    <h2><fmt:message key="menu.title" /></h2>
    <ul>
        <li><a href="login.jsp"><fmt:message key="menu.login" /></a></li>
        <li><a href="locadoras/list"><fmt:message key="menu.listLocadoras" /></a></li>
        <li><a href="locadora-por-cidade.jsp"><fmt:message key="menu.listLocadorasByCity" /></a></li>
    </ul>

    <form action="locale" method="get">
        <button type="submit" name="lang" value="pt">Português</button>
        <button type="submit" name="lang" value="en">English</button>
    </form>
</body>
</fmt:bundle>
</fmt:bundle>
</html>