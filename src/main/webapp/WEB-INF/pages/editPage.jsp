<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-05-29
  Time: 06:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <c:if test="${empty comp.name}">
        <title>Добавить</title>
    </c:if>
    <c:if test="${!empty comp.name}">
        <title>Редактировать</title>
    </c:if>
</head>
<body>
<c:if test="${empty comp.name}">
    <c:url value="/add" var="var"/>
</c:if>
<c:if test="${!empty comp.name}">
    <c:url value="/edit" var="var"/>
</c:if>

<c:if test="${empty comp.name}">
    <strong>Добавление</strong>
</c:if>
<c:if test="${!empty comp.name}">
    <strong>Редактирование</strong>
</c:if>
<br/>
<form action="${var}" method="POST">
    <c:if test="${!empty comp.name}">
        <input type="hidden" name="id" value="${comp.id}">
    </c:if>
    <label for="name">Название</label>
    <input type="text" name="name" id="name" value="${comp.name}">
    <br/>
    <label for="isNeeded">Необходим?
    <c:if test="${comp.needed == true}">
        <input type="checkbox" name="needed" id="isNeeded" value="${comp.needed}" checked>
    </c:if>
    <c:if test="${comp.needed != true}">
        <input type="checkbox" name="needed" id="isNeeded">
    </c:if>
    </label>
    <br/>
    <label for="quantity">Количество</label>
    <input type="text" name="quantity" id="quantity" value="${comp.quantity}">
    <br/>
    <c:if test="${empty comp.name}">
        <input type="submit" value="Добавить">
    </c:if>
    <c:if test="${!empty comp.name}">
        <input type="submit" value="Сохранить">
    </c:if>
</form>
</body>
</html>
