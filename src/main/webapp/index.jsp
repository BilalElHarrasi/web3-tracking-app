<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="domain.model.Person" %>
<%@ page import="domain.db.PersonService" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div id="container">
    <header class = "navigatie">
        <h1><span>Home, welkom</span></h1>
        <nav class = "header">
            <ul>
                <li id="actual"><a href="Controller?command=Index">Home</a></li>
                <li><a href="Controller?command=toOverzicht">Overzicht</a></li>
                <li><a href="Controller?command=toRegister">Register</a></li>
            </ul>
        </nav>

    </header>
    <main> Sed ut perspiciatis unde omnis iste natus error sit
        voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
        ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
        dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
        aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
        qui ratione voluptatem sequi nesciunt.
    </main>

    <form action="Controller?command=logIn" method="POST">
        <p><label for="userId">Your user id</label><input type="text" id="userId" name="userId" value="<c:out value="${userIdPrevious}"/>" required></p>
        <p><label for="password">Your password</label><input type="password" id="password" name="password" required></p>
        <p><input type="submit" id="login" value="Log in"></p>
    </form>

    <a href="Controller?command=LogOut">log out</a>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>