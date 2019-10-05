<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Data base</h2><br/>

<form method="get" action="/">
    <p><b>Edit user: </b></p>
    <table items="${requestScope.listE}" var="userEdit">
        <tr>
            <td>User name</td><td><input class="input-field" type="text" placeholder="${listE.name}" value="${listE.name}"><br></td>
        </tr>
        <tr>
            <td>Password</td><td><input class="input-field" type="password" id="password" name="password" value="${listE.password}"><br></td>
        </tr>
        <tr>
            <td>User e-mail</td><td><input class="input-field" type="text" id="email" name="email" value="${listE.email}"><br></td>
        </tr>
    </table>
    <input type="submit" value="Confirm" name='Ok'><br>
    <input type="hidden" name="id" value="${userEdit.id}">
</form>

</body>

</html>