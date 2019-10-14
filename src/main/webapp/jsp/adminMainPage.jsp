<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html;charset=windows-1251"
         pageEncoding="windows-1251"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

Work with - ${requestScope.with}
User - role :  <%= request.getSession().getAttribute("role") %>

<form method="POST" action="/admin">
    <p><b>Add new user</b></p>
    <table width="100%" cellspacing="0" cellpadding="4">
        <tr>
            <td align="right" width="100">Login</td>
            <td><input type="text" name="login"></td>
        </tr>
        <tr>
            <td align="right">Password</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td align="right">Email</td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="Add new user" name="Ok">
                <input type="submit" value="Back to login"  onclick="document.forms[0].action = '/'; return true;"><br>
            </td>
        </tr>
    </table>
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
            <td> ${user.user_id} </td>
            <td> ${user.name} </td>
            <td> ${user.password} </td>
            <td> ${user.email} </td>
            <td>
                <form method="GET" action="/edit">
                    <input type="submit" value="Edit" name="edit">
                    <input type="hidden" name="id" value="${user.user_id}">
                </form>
                <form method="GET" action="/delete">
                    <input type="submit" value="Delete" name="delete">
                    <input type="hidden" name="id" value="${user.user_id}">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>

</html>