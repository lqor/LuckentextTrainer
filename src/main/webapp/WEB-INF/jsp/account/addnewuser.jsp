<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style type="text/css"> <%@ include file="../../css/style.css" %> </style>
<style type="text/css"> <%@ include file="../../css/login.css" %> </style>

<html>
<head>
    <title>Create new User</title>
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
    <div class="form-wrap">
        <c:if test="${param.error == null && succCreateUser}">
            <i style="color: greenyellow;">Der neue Benutzer wurde erfolgreich erstellt und gespeichert. Melden Sie sich jetzt an:</i><br>
            <a href="/login" style="color: greenyellow;">
                Zur Anmeldung!
            </a>
        </c:if>

        <form:form action="${pageContext.request.contextPath}/createNewUser" method="POST">

        <c:if test="${param.error != null || !succCreateUser}">
            <i style="color: white;">Invalide name, email oder password</i>
        </c:if>

            <h1>Registrieren</h1>
            <input type="text" placeholder="Login" name="username">
            <input type="password" placeholder="Password" name="password">
            <input type="email" placeholder="Email" name="email">
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