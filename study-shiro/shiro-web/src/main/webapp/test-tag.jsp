<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<shiro:guest>
    ???????<a href="login.jsp">??</a>
</shiro:guest>

<shiro:user>
    ???<shiro:principal/>????<a href="logout">??</a>
</shiro:user>

<shiro:authenticated>
    ???<shiro:principal/>????????
</shiro:authenticated>

<shiro:notAuthenticated>
    ????????????
</shiro:notAuthenticated>

<shiro:principal property="username"/>

<shiro:hasRole name="admin">
    ???<shiro:principal/>? ???? admin
</shiro:hasRole>

<shiro:hasAnyRoles name="admin,user">
    ???<shiro:principal/>? ???? admin ? user
</shiro:hasAnyRoles>

<shiro:lacksRole name="admin">
    ???<shiro:principal/>? ???? admin
</shiro:lacksRole>

<shiro:hasPermission name="user:create">
    ???<shiro:principal/>????? user:create
</shiro:hasPermission>

<shiro:lacksPermission name="org:create">
    ???<shiro:principal/>????? org:create
</shiro:lacksPermission>
</body>
</html>