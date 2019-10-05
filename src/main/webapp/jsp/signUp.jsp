<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>Data base</h2><br/>

<div class="form-style-2">
    <div class="form-style-2-heading">
        Add new user:
    </div><br>
    <form method="post" action="/" items="${requestScope.listE}" var="userEdit">
    <table>
        <tr>
            <td>User name</td><td><input class="input-field" type="text" id="name" name="name" value="${listE.name}"><br></td>
        </tr>
        <tr>
            <td>Password</td><td><input class="input-field" type="password" id="password" name="password" value="${listE.password}"><br></td>
        </tr>
        <tr>
            <td>User e-mail</td><td><input class="input-field" type="text" id="email" name="email" value="${listE.email}"><br></td>
        </tr>
    </table>
        <input type="submit" value="Enter">
        <input type="hidden" name="id" value="${userEdit.id}">
    </form>
</div>

<table table border="1" cellspacing="0" cellpadding="2">
    <tr>
        <th>Id</th>
        <th>name</th>
        <th>password</th>
        <th>email</th>
        <th>action</th>
    </tr>
    <c:forEach items="${requestScope.listU}" var="user">
        <tr>
            <td> ${user.id} </td>
            <td> ${user.name} </td>
            <td> ${user.password} </td>
            <td> ${user.email} </td>
            <td>
                <form action="/jsp/edit.jsp">
                    <input type="submit" value="Edit" name="edit">
                    <input type="hidden" name="id" value="${user.id}">
                </form>
                <form method="get">
                    <input type="submit" value="Delete" name="delete">
                    <input type="hidden" name="id" value="${user.id}">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>


</body>

</html>