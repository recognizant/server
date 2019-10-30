<%@page import="Database.DBQuery"%>
<%

String user=request.getParameter("user");
String pass=request.getParameter("pass");
String email=request.getParameter("email");
String mob=request.getParameter("mob");
String fav=request.getParameter("fav");
DBQuery dbq=new DBQuery();
int i=dbq.regUserAD(user, email, pass, mob, fav);
String status="";
if(i==0)
       {
   
status="notok";
}
else{
   status="ok"; 
}
out.print(status);
%>