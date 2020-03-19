<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css"> <%@ include file="../../css/style.css" %> </style>
<style type="text/css"> <%@ include file="../../css/table.css" %> </style>
<style type="text/css"> <%@ include file="../../css/logoutbutton.css" %> </style>

<%--todo: refactor to directory course--%>
<html>
<head>
    <title>Aufgaben</title>
    <link
            href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700&display=swap"
            rel="stylesheet"
    />

    <link href="https://fonts.googleapis.com/css?family=IM+Fell+English&display=swap" rel="stylesheet">

    <style>
        .exercise-form {
            font-size: 25px;
        }
        input[type=text]:focus  {
            outline: none !important;
            border: 3px solid orange;
            box-shadow: 0 0 10px #e9eeff;
            font-size: 25px;

            border-radius: 5px;
        }
        input[type=text]  {
            font-size: 25px;
            border-color: orange;
            border-radius: 5px;
        }
    </style>
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

    <br><br>

    <main>
        <form:form class="exercise-form"
                   method="POST"
                   action="/course/ex/processform?number=${number}&points=${points}">
            Aufgabe <b>№ ${number}</b> <br> <br>
            ${text} <br>
            ${answers} <br><hr>

            <security:authorize access="hasAnyRole('USER', 'ADMIN')">
                Points für richtige Lösung: <b>${points}</b> <br>
            </security:authorize>

            <c:if test="${allDone && !userAlreadyDoneThisExercises}">
                <i style="color: orange">Sie haben alles richtig gelöst! Und Sie bekommen <b>alle</b> Punkte!</i> <br> <br>
            </c:if>

            <c:if test="${falseForms.size() >= 1}">
                <i style="color: red">Formen mit folgenden Nummern sind falsch: ${falseForms}</i> <br><br>
            </c:if>

            <c:if test="${userAlreadyDoneThisExercises}">
                <i style="color: orange">Sie haben alles richtig gelöst! Sie bekommen aber keine Punkte, da Sie früher diese Aufgabe schon gelöst haben</i> <br><br>
            </c:if>
            <input class="example_c" align="center" type="submit" rel="nofollow noopener" value="Überprüfen" />

        </form:form>
    </main>

</body>
</html>