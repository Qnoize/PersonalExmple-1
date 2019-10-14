<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html;charset=windows-1251"
         pageEncoding="windows-1251"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="POST" action="/edit">
    <p><b>Edit user with Id - ${user.id} </b></p>
    <table width="100%" cellspacing="0" cellpadding="4" items="${requestScope.user}">
        <tr>
            <td align="right" width="100">Login</td>
            <td><input type="text" name="login" value= ${user.name}></td>
        </tr>
        <tr>
            <td align="right">Password</td>
            <td><input type="password" name="password" value=${user.password}></td>
        </tr>
        <tr>
            <td align="right">Email</td>
            <td><input type="text" name="email" value=${user.email}></td>
        </tr>
    </table>
    <input type="submit" value="Confirm" name="Ok">
    <input type="hidden" name="id" value="${user.id}">
    <input type="submit" value="Back to admin" onclick="document.forms[0].action = '/admin'; return true;">
</form>

</body>
</html>
