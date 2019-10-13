<%@ page language="java" contentType="text/html;charset=windows-1251"
         pageEncoding="windows-1251"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
Hello   <%= request.getSession().getAttribute("login") %>
</br>
<form method="POST" action="/userHome">
    <input type="submit" value="Back to login" name="login" onclick="document.forms[0].action = '/'; return true;"><br>
</form>
</body>
</html>
