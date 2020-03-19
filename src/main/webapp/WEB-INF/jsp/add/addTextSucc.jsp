<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style type="text/css"> <%@ include file="../../css/style.css" %> </style>
<style type="text/css"> <%@ include file="../../css/logoutbutton.css" %> </style>
<style type="text/css"> <%@ include file="../../css/ruleList.css" %> </style>

<html>
<head>
    <title>Add new Text</title>
    <link href="https://fonts.googleapis.com/css?family=IM+Fell+English&display=swap" rel="stylesheet">
</head>
<body>
<header>
    <div class="container">
        <h1 class="logo">Lücken<sup>TRAINER</sup></h1>

        <nav>
            <ul>
                <li><a href="/">Architektur</a></li>
                <li><a href="/course">Üben</a></li>
                <li><a href="/top">Top 20 Benutzer</a></li>
                <li><a href="/account">KOnto</a></li>
                <security:authorize access="hasRole('ADMIN')">
                    <li><a href="/add">Text hinzufügen</a></li>
                </security:authorize>
            </ul>
        </nav>
    </div>
</header>

<main>
    <div style="font-size: 25px; color: orange;">
        <b>Sie haben erfolgreich den Text hinzugefügt!</b>
    </div>
</main>
</body>
</html>
