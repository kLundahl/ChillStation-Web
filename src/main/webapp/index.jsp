<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Tessellate by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Chill Station</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery.scrolly.min.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/init.js"></script>
		<noscript>
			<link rel="stylesheet" href="css/skel.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-wide.css" />
		</noscript>
		<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
		<!--[if lte IE 9]><link rel="stylesheet" href="css/ie/v9.css" /><![endif]-->
	</head>
	<body>
		<nav>
			<ul>
				<li><a href="#home">Home</a></li>
				<li><a href="/ChillStation/Register">Register</a></li>
				<li><a href="/ChillStation/Login">Login</a></li>
				<li><a href="/ChillStation/About">About</a></li>
				<li><a href="contact.html">Contact</a></li>
			</ul>
		</nav>
		<!-- Header -->
			<section id="header" class="dark">
				<header>
					<img src="pictures/logo.png" alt="Logo"/>
        			<h1>Chill Station</h1>
			        <p>Soundcloud & Youtube Streaming</p>
				</header>
			</section>
			<hr />
			
		<!-- First -->
			
				<form method="POST"  action="Login">
					<div id="first" class="row">
						<div class="3u"></div><!-- Spacer Div-->
						<div class="6u">
							<div class="inputclass row">
								Username:<input type="text" name="username">
							</div>
							<div class="inputclass row">
								Password:<input type="password" name="password">
							</div>
							<div class="row">
								<div class="8u">
									<a href="/passwordreset" class="inputclass">Forgot your Password?</a>
								</div>
								<div class="inputclass 4u">
									<input type="submit" value="Login"> 
								</div>
							</div>
						</div>
						<div class="3u"></div><!-- Spacer Div-->
						</div>
					</div>
				</form>
			

		
		<!-- Footer -->
			<section id="footer">
				<ul class="icons">
					<li><a href="http://www.twitter.com" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
					<li><a href="https://www.youtube.com/watch?v=cI-Xx1Eo1gw" class="icon fa-youtube"><span class="label">Youtube</span></a></li>
					<li><a href="https://soundcloud.com/bearsonmusic" class="icon fa-soundcloud"><span class="label">Soundcloud</span></a></li>
					<li><a href="https://www.github.com/samzwatkins/musicApp" class="icon fa-github"><span class="label">GitHub</span></a></li>
				</ul>
				<div class="copyright">
						Copyright &copy; 2014 | Chill Station | All Rights Reserved WorldWide.
				</div>
			</section>

	</body>
</html>