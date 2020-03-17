<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css"> <%@ include file="../../css/style.css" %> </style>
<style type="text/css"> <%@ include file="../../css/table.css" %> </style>
<html>
<head>
    <title>Kurs</title>
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
                    <li><a href="/">Home</a></li>
                    <li><a href="/course">Kurs</a></li> <%--todo: refactor to Trainer or Übung--%>
                    <li><a href="/top">Top 20 Benutzer</a></li>
                    <li><a href="/account">Account</a></li>

                    <security:authorize access="hasRole('ADMIN')">
                        <li><a href="/add">Add Text</a></li>
                    </security:authorize>
                </ul>
            </nav>
        </div>
    </header>

    <br><br>

    <main>
        <div class="container">
            <div class="wrapper">
                <table>
                    <thead>
                    <tr>
                        <th>Aufgaben</th>
                        <th>Points</th>
                        <th>Schwierigkeitsgrad</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="tempText" items="${texts}" varStatus="loop">
                            <tr>
                                <td class="rank">
                                    <a href="/course/ex?number=${tempText.id}" class="rowlink">
                                        Aufgabe ${tempText.id}
                                    </a>
                                    <b style="color: green;">
                                        <c:if test="${idOfDoneCourses.contains(tempText.id)}">&#10004;</c:if> <%--idOfDoneCourses.contains(tempText.id)--%>
                                    </b>
                                </td>
                                <td class="points"> ${tempText.points} </td>
                                <td class="diff"> ${tempText.difficulty} </td>
                            </tr>

                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
</body>
</html>
