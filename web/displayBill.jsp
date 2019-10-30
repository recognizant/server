<%-- 
    Document   : index
    Created on : Jan 11, 2016, 4:02:43 PM
    Author     : win 7
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.AALOAD"%>
<%@page import="Database.DBQuery"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
String projPath = request.getContextPath(); 

String message="",tid="";


if(session.getAttribute("msg")!=null)
       {

message=session.getAttribute("msg").toString();

}
if(session.getAttribute("tid")!=null)
       {

tid=session.getAttribute("tid").toString();

}
DBQuery dbq=new DBQuery();
ResultSet rs=dbq.readBillDetails(tid);
String user="",itemnames="",quantity="",amount="",total="",date="";
if(rs.next())
       {
//user=rs.getString("user");
 itemnames=rs.getString("items");
 quantity=rs.getString("quantity");
 amount=rs.getString("amount");
 total=rs.getString("total");
 date=rs.getString("date1");
}
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="<%= projPath%>/styles/layout.css" type="text/css">

</head>
<body id="top">
<div class="wrapper col1">
  <div id="header">
   
        <h1>RFID Store</a></h1>
    
    
      <br class="clear" />
  </div>
</div>
<div class="wrapper col2">
  <div id="topbar">
    <div id="topnav">
      <ul>
        <li class="active"><a href="storeHome.jsp">Home</a></li>
       
       
       
        <li><a href="StoreItemReg.jsp">Store Item Registration</a></li>
        <li><a href="StoreShelf.jsp">Shelf Details</a></li>
         <li><a href="ItemOffer.jsp">Item Offer</a></li>
         <li><a href="RfidReg.jsp">RFID Registration</a></li>
         <li><a href="StorePlan.jsp">Store Plan Registration</a></li>
         <li><a href="Billing.jsp">Item Bill</a></li>
         <li><a href="signout.jsp">Sign Out</a></li>
      </ul>
        </div>
</div>

</div>
   <div style="margin-top: 85px; margin-left: 83px;">
 <%=message%>
 
 <table>   
     <tr><td>TID= <%=tid%></td><td>User <%=user%></td><td>Date: <%=date%></td></tr>
     <tr><td>Items</td><td>Quantity</td><td>Amount </td></tr>
 <%
 String items[]=itemnames.split("-");
 String quan[]=quantity.split("-");
 String amnt[]=amount.split("-");
 for(int i=0;i<items.length;i++)
 
 {
 
 %>
 <tr><td><%=items[i]%></td><td><%=quan[i]%></td><td><%=amnt[i]%></td></tr>
 
 <%
 }
 %>
 <tr><td>Total  <%=total%></td></tr>
 </table>
  </div>
</body>
</html>
