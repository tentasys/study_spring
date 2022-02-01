<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1> memModifyOk </h1>
	Before : ${members[0].memId}<br />
	After : ${members[1].memId}<br />

	<P>  The time on the server is ${serverTime}. </P>
	
	<a href="/Project17/resources/html/index.html"> Go Main </a>
</body>
</html>
