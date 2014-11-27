<%-- 
    Document   : upload
    Created on : Sep 22, 2014, 6:31:50 PM
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
            <h3>File Upload</h3>
            <form method="POST" enctype="multipart/form-data" action="Image">
                File to upload: <input type="file" name="upfile"><br/>
				Select filter: 
				<input type="checkbox" name="filterSelector"/> Grayscale
				<br>
                <br/>
                <input type="submit" value="Press"> to upload the file!
            </form>

        </article>
        <footer>
            <ul>
                <li>&COPY;Paris Messios</li>
            </ul>
        </footer>
    </body>
</html>
