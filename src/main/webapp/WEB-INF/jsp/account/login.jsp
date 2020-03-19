<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style type="text/css"> <%@ include file="../../css/style.css" %> </style>
<style type="text/css"> <%@ include file="../../css/login.css" %> </style>

<html>
<head>
    <title>Login page</title>
    <link href="https://fonts.googleapis.com/css?family=IM+Fell+English&display=swap" rel="stylesheet">
</head>
<body>
<header>
    <div class="container">
        <h1 class="logo">
            Lücken<sup>TRAINER</sup>
        </h1>

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

            <form:form action="${pageContext.request.contextPath}/login"
                       method="POST">
                <c:if test="${param.error != null}">
                    <i style="color: white;">Versuchen Sie mit anderem Login oder Password</i>
                </c:if>

                <h1>Anmelden</h1>
                <input type="text" placeholder="Login" name="username"> <%--wichtig, ohne name parameter funktioniert es nicht--%>
                <input type="password" placeholder="Password" name="password">
                <hr>
                <input type="submit" value="Log in">
                <div style="color: white;">
                    Kein Account?

                    <a href="/addnewuser" style="color: white;">
                        Registrieren!
                    </a>
                </div>
            </form:form>

        </div>
    </main>
</body>
</html>


<%--
        <div class="form-wrap">

            <form>

                <h1>Sign Up</h1>
                <input type="text" placeholder="Username">
                <input type="password" placeholder="Password">
                <input type="email" placeholder="Email">
                <hr>
                <input type="button" value="Sign Up">
                <div>
                    Not a member?


                    <a href="#" class="txt2 bo1">
                        Sign up now
                    </a>
                </div>
            </form>

        </div>
--%>