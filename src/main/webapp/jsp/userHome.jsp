<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <title>Title</title>
</head>
<body>
Hello ${user.name}
</br>
<form method="POST" action="/userHome">
    <input type="submit" value="Exit" name="exit"><br>
    <input type="submit" value="Login" name="login" onclick="document.forms[0].action = '/'; return true;"><br>
</form>
</body>
</html>
