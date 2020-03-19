<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css"> <%@ include file="../../css/style.css" %> </style>
<style type="text/css"> <%@ include file="../../css/table.css" %> </style>
<style type="text/css"> <%@ include file="../../css/logoutbutton.css" %> </style>


<html>
<head>
    <title>Account</title>
    <link
            href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700&display=swap"
            rel="stylesheet"
    />

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
        <sec:authorize access="!isAuthenticated()">
            <h1>Sie sind leider nicht angemeldet!</h1> <br><br>

            <div class="button_cont" align="center">
                <a class="example_c" href="/login" rel="nofollow noopener">Anmelden</a>
            </div>
            <div class="button_cont" align="center">
                <a class="example_c" href="/addnewuser" rel="nofollow noopener">Account erstellen</a>
            </div>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <h1>Sie sind auf der Account-Seite, hier finden Sie Information zu ihrem Account</h1> <br>
            <hr style="background-color: orange;">

            <p>Sie sind als <b>${username}</b> angemelden</p> <br>
            <security:authorize access="hasRole('ADMIN')">
                <p>Sie haben <b>ADMIN</b> Zugang, das heißt sie dürfen neue Texte hinzufügen!</p> <br>
            </security:authorize>

            <p>Erreichte Points: <b>${points}</b> </p> <br>

            <p>
                Vollständig gelöste Aufgaben:
                <c:if test="${doneexercises == null || doneexercises.length() == 0}"> Sie haben leider noch <b>keine</b> Aufgabe gelöst</c:if>
                <c:if test="${doneexercises != null}"> ${doneexercises} </c:if>

            </p> <br>

            <div class="button_cont" align="center">
                <a class="example_c" href="/logout" rel="nofollow noopener">Logout</a>
            </div>
        </sec:authorize>
    </main>
</body>
</html>