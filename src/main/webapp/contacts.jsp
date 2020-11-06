<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@page import="domain.db.PersonService" %>
<%@page import="domain.model.Person" %>
<%@page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>

<%PersonService ps = new PersonService();%>
<%ArrayList<Person> lijstVanPersonen = (ArrayList<Person>) ps.getAll();%>
<%--ArrayList<Person> lijstVanPersonen = (ArrayList<Person>) request.getAttribute("personen");%>--%>
<%--to do what?--%>


<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Overview</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
<div id="container">
    <header class="navigatie">
        <h1><span>Overzicht</span></h1>
        <nav class="header">
            <ul>
                <li><a href="Controller?command=Index">Home</a></li>
                <li id="actual"><a href="Controller?command=toOverzicht">Overzicht</a></li>
                <li><a href="Controller?command=toRegister">Register</a></li>
            </ul>
        </nav>

    </header>
    <main>
        <div class="middenGrid">
            <table>
                <tr>
                    <%for (Person persoon : lijstVanPersonen) {%>
                    <section>
                        <article>
                            <p class="verklaring">naam</p>
                            <p class="data"><%=persoon.getFirstName() %>
                            </p>
                        </article>

                        <article>
                            <p class="verklaring">achternaam</p>
                            <p class="data"><%=persoon.getLastName() %>
                            </p>
                        </article>

                        <article>
                            <p class="verklaring">email</p>
                            <p class="data"><%=persoon.getEmail() %>
                            </p>
                        </article>

                        <%--<article>--%>
                            <%--<p class="verklaring">verdiep</p>--%>
                            <%--<p class="data"><%=persoon.getVerdiep() %>--%>
                            <%--</p>--%>
                        <%--</article>--%>

                        <%--<p><a href="Controller?actie=update&id=<%= persoon.getSerie() %>">bewerk skateboard</a></p>--%>
                        <%--<p><a href="Controller?actie=naarVerwijder&serie=<%=skateboard.getSerie()%>">Verwijder skateboard</a></p>--%>

                    </section>

                    <%} %>
                </tr>

                <caption>Users Overview</caption>
            </table>
        </div>
    </main>
    <footer>
        &copy; Bilal El Harrasi, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>