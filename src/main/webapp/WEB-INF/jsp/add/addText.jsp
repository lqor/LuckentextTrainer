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
        <div style="font-size: 25px">
            Hier könne Sie neue Aufgaben hinzufügen. Beachten Sie dabei folgende <b>Regeln:</b> <br> <br>
        </div>
        <ul class="push">
            <li>Um ein oder mehrere Wörter als Lücke zu markieren, fügen Sie "@" am Anfang und Ende der Wörter-Kette</li>
            <li>Beispiel: "Ich @trinke@ Coffee gern" wird in der Aufgabe zu: "Ich _____ Coffee gern"</li>
            <li>Achten Sie bitte, dass der Text kein "@" Zeichen enthält</li>
            <li>Technisch gesehen, gibt es keine Einschränkung für die Lücken-Länge</li>
            <li>Vermeiden Sie aber Lücken mit der Länge über 2 Wörter</li>
        </ul>

        <form:form class="text-form"
                   method="POST"
                   action="/addNewText">
            Points für gelöste Aufgabe: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" name="points" required /> <br><br>

            Schwierigkeitsgrad der Aufgabe:
            <input type="text" name="difficulty"  required /> <br><br>
            Geben Sie bitte den Text unten ein: <br>

            <textarea id="text" name="text"></textarea>

            <br><br>

            <input class="example_c" align="center" type="submit" rel="nofollow noopener" value="Hinzufügen" />
        </form:form>

        <br><br><br><br>
    </main>
</body>
</html>
