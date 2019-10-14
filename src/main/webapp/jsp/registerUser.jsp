<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=windows-1251"
         pageEncoding="windows-1251"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="POST" action="/register">
    <p><b>Register new User</b></p>
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
                <input type="submit" value="Registration" name="Ok">
                <input type="submit" value="Back to login"  onclick="document.forms[0].action = '/'; return true;"><br>
            </td>
        </tr>
    </table>

</form>

<table table border="1" cellspacing="0" cellpadding="2">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
    </tr>
    <c:forEach items="${requestScope.list}" var="user">
        <tr>
            <td> ${user.user_id} </td>
            <td> ${user.name} </td>
            <td> ${user.email} </td>
        </tr>
    </c:forEach>
</table>

</body>

</html>
