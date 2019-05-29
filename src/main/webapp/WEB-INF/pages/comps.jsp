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

<h3>Комплектующие</h3>
<table>
    <tr>
        <th>id</th>
        <th>название</th>
        <th>необходимы</th>
        <th>количество</th>
    </tr>
<c:if test="${compsList.size()>0}">
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
</c:if>
</table>

<p><strong>Страницы:</strong></p>
<c:forEach begin="1" end="${pagesCount}" step="1" varStatus="i">
    <c:url value="/" var="url">
        <c:param name="page" value="${i.index}"/>
    </c:url>
    <a href="${url}&search=${search}">${i.index}</a>
</c:forEach>

<p><strong>Фильтры и сортировка</strong></p>
По имени:
<a href="/?search=orderNameAsc&page=${page}">по возрастанию</a> /
<a href="/?search=orderNameDesc&page=${page}">по убыванию</a>
<br />
По необходимости:
<a href="/?search=orderNeedAsc&page=${page}">по возрастанию</a> /
<a href="/?search=orderNeedDesc&page=${page}">по убыванию</a>
<br />
По количеству:
<a href="/?search=orderQuantityAsc&page=${page}">по возрастанию</a> /
<a href="/?search=orderQuantityDesc&page=${page}">по убыванию</a>
<br />
Фильтры:
<a href="/?search=filterNeed&page=${page}">необходимы</a> /
<a href="/?search=filterNotNeed&page=${page}">опциональные</a> /
<a href="/?search=&page=${page}">все</a>

<p><strong>Компьютеров можно собрать:</strong></p>
${compsReadyCount}

<p><strong>Добавить новую позицию:</strong></p>
<c:url value="/add" var="add"/>
<a href="${add}">Добавить</a>

<p><strong>Поиск по полному совпадению имени</strong></p>
<form action="/search">
    <p><input type="text" name="search"> <input type="submit" name="Поиск" value="Найти"></p>
</form>
</body>
</html>
