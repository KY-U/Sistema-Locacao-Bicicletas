<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listar Locadoras por Cidade</title>
</head>
<body>
    <h2>Locadoras por Cidade</h2>
    <form action="locadoras/cidades" method="get">
        <label for="cidade">Cidade:</label>
        <input type="text" id="cidade" name="cidade" required>
        <button type="submit">Procurar Locadoras</button>
    </form>
</body>
</html>