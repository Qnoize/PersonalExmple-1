<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Work with - ${requestScope.with}
<form method="POST">
    <p><b>Add user</b></p>
    <table width="100%" cellspacing="0" cellpadding="4">
        <tr>
            <td align="right" width="100">Name</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td align="right">Password</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td align="right">Email</td>
            <td><input type="text" name="email"></td>
        </tr>
    </table>
    <input type="submit" value="Confirm" name="Ok"><br>
</form>

<table table border="1" cellspacing="0" cellpadding="2">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Password</th>
        <th>Email</th>
    </tr>
    <c:forEach items="${requestScope.list}" var="user">
        <tr>
            <td> ${user.id} </td>
            <td> ${user.name} </td>
            <td> ${user.email} </td>
            <td>
                <form method="GET" action="/edit">
                    <input type="submit" value="Edit" name="edit">
                    <input type="hidden" name="id" value="${user.id}">
                </form>
                <form method="GET" action="/delete">
                    <input type="submit" value="Delete" name="delete">
                    <input type="hidden" name="id" value="${user.id}">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>

</html>