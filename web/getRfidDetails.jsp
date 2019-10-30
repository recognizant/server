<%@page import="Database.DBQuery"%>
<%

String r=request.getParameter("rfid");
System.out.println("............................................................."+r);

DBQuery dbq=new DBQuery();
System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
String details=dbq.getRFIDDescription(r);
System.out.println(">>>>>>>>>"+details);
out.print(details);
%>