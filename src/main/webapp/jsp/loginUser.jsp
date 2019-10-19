<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html;charset=windows-1251"
         pageEncoding="windows-1251"%>
<html>
<body>
<form method="POST" action="/">
    <p><b>Autorization user </b></p>
    <table width="100%" cellspacing="0" cellpadding="4">
        <tr>
            <td align="right" width="100">Login</td>
            <td><input class="input-field"  type="text" name="login"></td>
        </tr>
        <tr>
            <td align="right">Password</td>
            <td><input class="input-field" type="password" name="password"></td>
        </tr>
        <tr>
            <td></td>
            <td align="left" width="100">
                <input type="submit" value="Enter" name="Ok">
                <button><a href="/register" style="text-decoration: none; color: black;">Registration</a></button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
