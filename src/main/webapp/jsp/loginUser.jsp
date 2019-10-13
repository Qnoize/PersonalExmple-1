<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html;charset=windows-1251"
         pageEncoding="windows-1251"%>
<html>
<body>
<div class="form-style-2-heading">
</div>
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
                <input type="submit" value="Confirm" name="Ok">
                <input type="submit" value="Registration" name="register" onclick="document.forms[0].action = '/register'; return true;"><br>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
