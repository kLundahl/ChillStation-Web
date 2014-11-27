<%-- 
    Document   : login.jsp
    Created on : Sep 28, 2014, 12:04:14 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instatoons.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>instatoons</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />

    </head>
    <body>
        <header>
        	<h1>
            	<a class="header-logo" href="/instatoons-PMessios">instatoons</a>
            </h1>
        </header>
             <div id="wrapper">
			<div class='ribbon'>
               
                <a href="/instatoons-PMessios"><span>&#10029;Home</span></a>
                
                    <%
                    LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        			int logged=0;
                    if (lg != null) {
                        String UserName = lg.getUsername();
                        logged=1;
                        if (logged == 1) {
                            if (lg.getlogedin()) {
                    %>
                <a href="/instatoons-PMessios/Profile"><span>&#10029;Profile</span></a>
                <a href="/instatoons-PMessios/Upload"><span>&#10029;Upload</span></a>
                <a href="/instatoons-PMessios/Images/<%=lg.getUsername()%>"><span>&#10029;Your Images</span></a>
                    <%}}
                            }else{
                                %>
                 <a href="/instatoons-PMessios/Register"><span>&#10029;Register</span></a>
                <a href="/instatoons-PMessios/Login"><span>&#10029;Login</span></a>
                <%}%>
                   <a href="/instatoons-PMessios/About"><span>&#10029;About</span></a>
                    
		</div>
		</div>
       
        <article>
        <%
        if (logged == 1) {
            if (lg.getlogedin()) {%>
            <p>
            <h1>Hello <%=lg.getUsername()%>! <br>
            <img src="/instatoons-PMessios/Pictures/Avatars/<%=lg.getAvatar()%>.png" alt="No avatar icon found" />
            <br>
            <br>
            Here are your details:</h1>
            <h2>Name: <%=lg.getFullName()%> <br>
            Date of Birth: <%=lg.getDoB()%> <br>
            E-mai: <%=lg.getEmail()%> <br></h2>
            <%}}%>


        </article>
        <footer>
            <ul>
                <li>&COPY;Paris Messios</li>
            </ul>
        </footer>
    </body>
</html>
