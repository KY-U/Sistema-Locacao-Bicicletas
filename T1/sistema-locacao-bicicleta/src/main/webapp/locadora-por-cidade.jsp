<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<fmt:bundle basename = "messages">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="list_locadoras_by_city_title"/></title>
</head>
<body>
    <h2><fmt:message key="list_locadoras_by_city_title"/></h2>
    <form action="locadoras/cidades" method="get">
        <label for="cidade"><fmt:message key="city_label"/></label>
        <input type="text" id="cidade" name="cidade" required>
        <button type="submit"><fmt:message key="search_locadoras_button"/></button>
    </form>
</body>
</fmt:bundle>
</html>
