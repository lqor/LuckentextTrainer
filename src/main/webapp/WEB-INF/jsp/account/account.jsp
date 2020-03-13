<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css"> <%@ include file="../../css/style.css" %> </style>
<style type="text/css"> <%@ include file="../../css/table.css" %> </style>

<html>
<head>
    <title>Account</title>
    <link
            href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700&display=swap"
            rel="stylesheet"
    />

</head>
<body>
    <header>
        <div class="container">
            <h1 class="logo"></h1>

            <nav>
                <ul>
                    <li><a href="/">Home</a></li>
                    <li><a href="/course">Kurs</a></li>
                    <li><a href="/top">Top Benutzer</a></li>
                    <li><a href="/info">Infomation/FAQ</a></li>
                    <li><a href="/account">Account</a></li>
                    <security:authorize access="hasRole('ADMIN')">
                        <li><a href="/add">Add Text</a></li>
                    </security:authorize>
                    <security:authorize access="hasAnyRole('ADMIN','USER')">
                        <div class="userName"><li>User: ${username}</li></div>
                    </security:authorize>
                </ul>
            </nav>
        </div>
    </header>

    <main>
        <sec:authorize access="!isAuthenticated()">
            <a href="/login">Go to Login</a>
            <a href="/addnewuser">Create new user</a>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <a href="/logout">Logout</a>
        </sec:authorize>
    </main>
</body>
</html>