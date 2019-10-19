<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html;charset=windows-1251"
         pageEncoding="windows-1251"%>
<html>
<body>
<form method="POST" action="/admin/edit">
    <p><b>Edit user with Id - ${user.userId} </b></p>
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
        <tr>
            <td></td>
            <td>
                <input type="submit" value="Confirm" name="Ok">
                <input type="hidden" name="userId" value="${user.userId}">
                <button><a href="/admin" style="text-decoration: none; color: black;">Back to admin</a></button>
             </td>
        </tr>
    </table>
</form>
</body>
</html>
