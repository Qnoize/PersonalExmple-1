<%@ page language="java" contentType="text/html;charset=windows-1251"
         pageEncoding="windows-1251"%>
<html>
<body>
Hello  ${requestScope.userLogin}<br>
User - role :   ${requestScope.userRole}
</br>
<form method="POST" action="/userHome">
    <button><a href="/" style="text-decoration: none; color: black;">Back to login</a></button>
</form>
</body>
</html>
