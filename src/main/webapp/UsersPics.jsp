<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instatoons.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>instatoons</title>
        <link rel="stylesheet" type="text/css" href="/instatoons-PMessios/Styles.css" />
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
                            //String UserName = lg.getUsername();
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
            <h1>Your Pics</h1>
        <%
            java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
            if (lsPics == null) {
        %>
        <p>No Pictures found</p>
        <%
        } else {
            Iterator<Pic> iterator;
            iterator = lsPics.iterator();
            while (iterator.hasNext()) {
                Pic p = (Pic) iterator.next();

        %>
        <a href="/instatoons-PMessios/Image/<%=p.getSUUID()%>" ><img src="/instatoons-PMessios/Thumb/<%=p.getSUUID()%>"></a><br/><%

            }
            }
        %>
        </article>
        <footer>
            <ul>
                <li>&COPY;Paris Messios</li>
            </ul>
        </footer>
    </body>
</html>
