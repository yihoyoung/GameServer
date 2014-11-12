<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isErrorPage="true" session="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    <h1>Error500 in WAS</h1>
	<%
		out.print( request.getRemoteAddr() + ", " );
		out.print( request.getRemoteHost() + ", " );
		out.print( request.getServerName() + ", " );
	%>
	<hr/>
    	${requestScope['javax.servlet.error.message']}
	<c:out value="${requestScope['javax.servlet.error.message']}"/>
	<hr/>
	<%@ page import="java.io.*" %>
	<I><%= exception %></I>. This problem occurred in the following place:
	<PRE>
		<% out.println("exPage=" + pageContext.getException()); %>
	</PRE>
	<% 
		int i=0;
		out.println("<br/>"+ (++i)+" : ");
	    out.println(pageContext.getException());
		out.println("<br/>"+ (++i)+" : ");
	    out.println(pageContext.getErrorData().getRequestURI());
		out.println("<br/>"+ (++i)+" : ");
	    out.println(pageContext.getErrorData().getStatusCode());
		out.println("<br/>"+ (++i)+" : ");
	    out.println(pageContext.getException().getStackTrace());
		out.println("<br/>"+ (++i)+" : ");
	    out.println(pageContext.getException().getMessage() );
		out.println("<br/>"+ (++i)+" : ");
		//out.println(pageContext.getException().printStackTrace()
		pageContext.getException().printStackTrace();
		out.println("<br/>"+ (++i)+" : ");

		out.println("<br/>"+ (++i)+" : ");
		out.println("<br/>"+ (++i)+" : ");
		out.println("<br/>"+ (++i)+" : ");
		out.println("<br/>"+ (++i)+" : ");
		
	%>
</body>
</html>
