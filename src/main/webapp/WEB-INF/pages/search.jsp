<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2019-05-29
  Time: 06:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Comps</title>
</head>
<body>

<h2>Результаты поиска</h2>
<table>
    <tr>
        <th>id</th>
        <th>название</th>
        <th>необходимы</th>
        <th>количество</th>
    </tr>
    <c:forEach var="comp" items="${compsList}">
        <tr>
            <td>${comp.id}</td>
            <td>${comp.name}</td>

            <c:if test="${comp.needed}">
                <td>да</td>
            </c:if>
            <c:if test="${!comp.needed}">
                <td>нет</td>
            </c:if>

            <td>${comp.quantity}</td>
            <td>
                <a href="/edit/${comp.id}">редактировать</a>
                <a href="/delete/${comp.id}">удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
