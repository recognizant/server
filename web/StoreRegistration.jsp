<%-- 
    Document   : StoreRegistration
    Created on : Jan 11, 2016, 4:24:26 PM
    Author     : win 7
--%>

<%@page import="Database.DBQuery"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
String projPath = request.getContextPath(); 

String msg="";

if(session.getAttribute("msg")!=null)
       {

msg=session.getAttribute("msg").toString();

}

DBQuery dbq=new DBQuery();
int count =dbq.getStoreCount();
System.out.println("value of q"+ count);
count++;
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
    
        <h1>Blind Shopping OCR</a></h1>
    
    
      <br class="clear" />
  </div>
</div>
<div class="wrapper col2">
  <div id="topbar">
    <div id="topnav">
      <ul>
        <li class="active"><a href="index.jsp">Home</a></li>
        <li><a href="Login.jsp">Login</a></li>
      </ul>
        </div>
</div>

</div>
      <%=msg%>
   <div style="margin-top: 85px; margin-left: 83px;">

       <table>
           <form action="./storeRegistration" method="post">
               <tr>
                   <td><label>Store Id</label></td>
                   <td><input type="text" name="sid" id="sid" value="<%=count%>" readonly="true"/></td>
               </tr>
               <tr>
                   <td><label>Store Name</label></td>
                   <td><input type="text" name="sname" id="sname"/></td>
               </tr>
               <tr>
                   <td><label>Store Address</label></td>
                   <td><textarea name="sadd" id="sadd"></textarea></td>
               </tr>
               
               <tr>
                   <td>
                       Password
                   </td>
                   <td>
                       <input type="password" name="pass" id="pass"/>
                   </td>
               </tr>
                <tr>
                   <td>
                       Confirm Password
                   </td>
                   <td>
                       <input type="password" name="cpass" id="cpass"/>
                   </td>
               </tr>
               <tr>
                   <td></td> <td><input type="submit" name="submit" value="Register"</td>
               </tr>
           </form>
           
       </table>   
  
  </div>
</body>
</html>
