<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%request.setCharacterEncoding("UTF-8");%>
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
    </table>
    <input type="submit" value="Confirm" name="Ok">
    <input type="submit" value="Login"  onclick="document.forms[0].action = '/'; return true;"><br>
</form>

<table table border="1" cellspacing="0" cellpadding="2">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
    </tr>
    <c:forEach items="${requestScope.list}" var="user">
        <tr>
            <td> ${user.id} </td>
            <td> ${user.name} </td>
            <td> ${user.email} </td>
        </tr>
    </c:forEach>
</table>

</body>

</html>
