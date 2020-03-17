<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css"> <%@ include file="../../css/style.css" %> </style>
<style type="text/css"> <%@ include file="../../css/table.css" %> </style>


<html>
<head>
    <title>Top</title>
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
                    <li><a href="/course">Kurs</a></li>
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
                        <th>Rank</th>
                        <th>User</th>
                        <th>Points</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="tempUser" items="${top_users}" varStatus="loop">
                            <tr>
                                <td class="rank">${loop.count}</td>
                                <td class="team"> ${tempUser.username} </td>
                                <td class="points"> ${tempUser.points} </td>
                            </tr>

                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </main>

</body>
</html>
