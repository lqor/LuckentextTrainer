<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style type="text/css"> <%@ include file="../../css/style.css" %> </style>
<style type="text/css"> <%@ include file="../../css/login.css" %> </style>

<html>
<head>
    <title>Create new User</title>
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
    <div class="form-wrap">
        <c:if test="${param.error == null && succCreateUser}">
            <i style="color: white;">Der neue Benutzer wurde erfolgreich erstellt und gespeichert. Melden Sie sich jetzt an:</i><br>
            <a href="/login" style="color: orange;">
                Zur Anmeldung!
            </a>
        </c:if>

        <form:form action="${pageContext.request.contextPath}/createNewUser" method="POST">

        <c:if test="${succCreateUser == false}">
            <i style="color: white;">Invalide name, email oder password</i>
        </c:if>

            <h1>Registrieren</h1>
            <input type="text" placeholder="Login" name="username" required>
            <input type="password" placeholder="Password" name="password" required>
            <input type="email" placeholder="Email" name="email" required>
            <hr>
            <input type="submit" value="Login">
            <div style="color: white;">
                Hast schon einen Account

                <a href="/login" style="color: white;">
                    Melde dich hier an!
                </a>
            </div>
        </form:form>

    </div>
</main>
</body>
</html>