<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style type="text/css"> <%@ include file="../../css/style.css" %> </style>

<html>
<head>
    <title>Add new Text</title>
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

    </main>
</body>
</html>
