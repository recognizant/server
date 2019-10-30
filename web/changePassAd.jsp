
<%@page import="Database.DBQuery"%>
<%
String uid=request.getParameter("userid").toString();
String op=request.getParameter("op");
String np=request.getParameter("np");
System.out.println(""+uid+"---"+op+"--"+np);
DBQuery dbq=new DBQuery();
String status=dbq.updatePass(uid, op, np);
 out.print(status);
%>